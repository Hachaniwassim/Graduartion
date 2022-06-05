package it.igesa.resources;
import it.igesa.config.CaptchaService;
import it.igesa.domaine.Account;
import it.igesa.domaine.Role;
import it.igesa.enumerations.ERole;
import it.igesa.enumerations.AccountStatus;
import it.igesa.services.UserDetailsImpl;
import it.igesa.payload.request.LoginRequest;
import it.igesa.payload.request.SignupRequest;
import it.igesa.payload.response.JwtResponse;
import it.igesa.payload.response.MessageResponse;
import it.igesa.repository.IgroupeRepository;
import it.igesa.repository.RoleRepository;
import it.igesa.repository.AccountRepository;
import it.igesa.security.jwt.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.config.HttpResponse;
import it.igesa.config.EmailService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.validation.Valid;
import static org.springframework.http.HttpStatus.OK;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Tarchoun Abir
 *
 **/

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "Authentification" )
public class AuthController {

	/***************************
	 *
	 * @Api private with token
	 *
	 */
	private final String PUBLIC_API = "/api/auth";


	/** email Send **/
	public static final String EMAIL_SENT = "An email with a new password was sent to: ";

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
    AccountRepository userRepository;
	@Autowired
    RoleRepository roleRepository;
	@Autowired
    IgroupeRepository igroupeRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	private EmailService emailService;
	@Autowired
	private CaptchaService captchaService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public AuthController(AuthenticationManager authenticationManager, AccountRepository userRepository, RoleRepository roleRepository, IgroupeRepository igroupeRepository, PasswordEncoder encoder, JwtUtils jwtUtils, EmailService emailService, CaptchaService captchaService) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.igroupeRepository = igroupeRepository;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
		this.emailService = emailService;
		this.passwordEncoder = passwordEncoder;
		this.captchaService = captchaService;
	}

	@PostMapping(  PUBLIC_API + "/signin")
	@ApiOperation(value = "Signin", notes = "login  ", response = LoginRequest.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "signin sucessfully"),
			@ApiResponse(code = 400, message = "bad resquest"),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed"),

	})
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(

				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		/*boolean captchaVerified = captchaService.verify(loginRequest.getRecaptchaResponse());
		if (!captchaVerified) {
			throw new RuntimeException(" erreur captcha not valid");
		} else if (captchaVerified) {
			return ResponseEntity.ok(" valid captcha");
		}*/

		if (userDetails.getAccountStatus() == AccountStatus.ACTIVE){
			System.out.println(userDetails);
			return ResponseEntity.ok(new JwtResponse(jwt,
					userDetails.getId(),
					userDetails.getUsername(),
					userDetails.getEmail(),
					roles, userDetails.getFiscaleCode(),
					userDetails.getAccountStatus(),
					userDetails.getGroupeId()
					));

		}
		if (userDetails.getAccountStatus() == AccountStatus.PENDING) {
			return (ResponseEntity<?>) ResponseEntity.ok("ERREUR YOUR ACCOUNT STILL NOT ACTIVE");

		}
		else if (userDetails.getAccountStatus() == AccountStatus.BLOCKED) {
			return (ResponseEntity<?>) ResponseEntity.ok("ERREUR YOUR ACCOUNT IS BLOCKED");
		}
		return null;
	}




	@PostMapping(PUBLIC_API + "/signup")
	@ApiOperation(value = "Signin", notes = "signin", response = SignupRequest.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "registred sucessfully"),
			@ApiResponse(code = 400, message = "bad resquest"),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed"),

	})
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {


		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}


		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}



		// Create new user's account
		//initialisation of role and status ===>
		signUpRequest.setAccountStatus(AccountStatus.PENDING);
		signUpRequest.setRole(Collections.singleton("ROLE_USER"));
		Account user =(new Account(
				signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()),
				encoder.encode(signUpRequest.getMatchingPassword()),
				signUpRequest.getFiscaleCode(),
				signUpRequest.getAccountStatus()
		));


		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {


			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);

		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					case "mod":
						Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(modRole);

						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse(" your registered successfully!"));
	}


	@GetMapping( PUBLIC_API + "/resetpassword/{email}")
	@ApiOperation(value = "Reset Password", notes = "reset password", response = Account.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "reset passwordsucessfully"),
			@ApiResponse(code = 400, message = "bad resquest"),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed"),

	})
	public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email)  throws MessagingException {
		Account user = userRepository.findByEmail(email);
		String password = generatePassword();
		user.setPassword(encodePassword(password));
		userRepository.save(user);
		emailService.sendNewPasswordEmail(user.getUsername(), password, user.getEmail());
		return response(OK, EMAIL_SENT + email);
	}
	private String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}
	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}
	private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
		return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
				message), httpStatus);
	}

}
