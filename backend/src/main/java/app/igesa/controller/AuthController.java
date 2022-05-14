package app.igesa.controller;
import app.igesa.entity.Account;
import app.igesa.entity.ERole;
import app.igesa.entity.Role;
import app.igesa.enumerations.AccountStatus;
import app.igesa.metiers.UserDetailsImpl;
import app.igesa.payload.request.LoginRequest;
import app.igesa.payload.request.SignupRequest;
import app.igesa.payload.response.JwtResponse;
import app.igesa.payload.response.MessageResponse;
import app.igesa.repository.IgroupeRepository;
import app.igesa.repository.RoleRepository;
import app.igesa.repository.AccountRepository;
import app.igesa.security.jwt.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Tarchoun Abir#
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(tags = "Authentification" )
@RequestMapping("/api/auth")
public class AuthController {
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

	@PostMapping("/signin")
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


		 if (userDetails.getAccountStatus() == AccountStatus.ACTIVE){

			System.out.println(userDetails);
			return ResponseEntity.ok(new JwtResponse(jwt,
					userDetails.getId(),
					userDetails.getUsername(),
					userDetails.getEmail(),
					roles, userDetails.getFiscaleCode(),
					userDetails.getAccountStatus(), userDetails.getGroupeId()));

		}
		 if (userDetails.getAccountStatus() == AccountStatus.PENDING) {
			return (ResponseEntity<?>) ResponseEntity.ok("ERREUR YOUR ACCOUNT STILL NOT ACTIVE");

		}
		else if (userDetails.getAccountStatus() == AccountStatus.BLOCKED) {
			return (ResponseEntity<?>) ResponseEntity.ok("ERREUR YOUR ACCOUNT IS BLOCKED");}
		return null;
	}




	@PostMapping("/signup")
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
		signUpRequest.setAccountStatus(AccountStatus.PENDING);
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




}
