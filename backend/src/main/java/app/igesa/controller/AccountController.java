
package app.igesa.controller;
import app.igesa.config.EmailService;
import app.igesa.config.HttpResponse;
import app.igesa.dto.AccountDTO;
import app.igesa.dto.EntrepriseDTO;
import app.igesa.entity.Account;
import app.igesa.metiers.IauthService;
import app.igesa.payload.request.AssignRequest;
import app.igesa.payload.request.ChangePasswordRequest;
import app.igesa.enumerations.AccountStatus;
import app.igesa.metiers.implement.AccountImp;
import app.igesa.payload.request.UpdateProfilRequest;
import app.igesa.payload.response.MessageResponse;
import app.igesa.repository.AccountRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.mail.MessagingException;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author Tarchoun Abir
 *
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {

    public static final String EMAIL_SENT = "An email with a new password was sent to: ";


    /**************************
     *
     *@PRIVATE_API : with token
     *
     ******/
    private final String PRIVATE_API = "/api/private/user";

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    IauthService accountService ;
    private BCryptPasswordEncoder passwordEncoder;



    @RequestMapping(value = PRIVATE_API, method = RequestMethod.POST)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value = "ADD User", notes = "SAUVGARDER USER", response = Account.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "user was saved Successfully"),
            @ApiResponse(code = 400, message = "user not valid"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    ResponseEntity<AccountDTO> save(@RequestBody AccountDTO account) {
        log.debug(" HTTP POST {}", account);
        return new ResponseEntity<>(accountService.save(account), HttpStatus.CREATED);
    }


    @RequestMapping(value = PRIVATE_API, method = RequestMethod.GET)
    @ApiOperation(value = "GET A LIST OF USERS", responseContainer = "Collection<Account>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Role  was found successfully"),
            @ApiResponse(code = 404, message = "Role Not found "),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    public ResponseEntity<Collection<AccountDTO>>view() {
        log.debug(" HTTP GET ALL {}");
        return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }


    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ApiOperation(value = " GET USER BY ID ", notes = "GET AND SEARCH FOR ROLE  BY ID ", response = Account.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was found successfully with the provided id"),
            @ApiResponse(code = 404, message = "No User is found with the provided id "),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    public ResponseEntity <AccountDTO> findById(@PathVariable Long id) {
        log.debug(" HTTP GET  BY ID {}", id);
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }



    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value = PRIVATE_API, method = RequestMethod.PUT)
    @ApiOperation(value = "UPDATE User BY ID ", response = Account.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was updated successfully"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    public ResponseEntity<AccountDTO> update(@RequestBody AccountDTO user ) {
        return new ResponseEntity<>(accountService.save(user),HttpStatus.CREATED);
    }

    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value = PRIVATE_API + "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "DELETE User BY ID ", response = Account.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = " User was Deleted successfully"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed")

    })
    public ResponseEntity delete(@PathVariable Long id) {
        log.debug(" HTTP DELETE BY ID {}", id);
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value = PRIVATE_API + "/toggle-status/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "UPDATE USER BY Status", response = AccountDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "USER Status was updated successfully"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),
    })
    public Account updateSatus(@PathVariable("id")  Long id, @RequestBody AccountStatus status) throws MessagingException {
        return accountService.updateSatus(id, status);
    }

    // <=============change password=====================>
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping(PRIVATE_API +"/change-password")
    @ApiOperation(value = "Change Password", notes = "change password", response = AccountDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "change password sucessfully"),
            @ApiResponse(code = 400, message = "bad resquest"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    public boolean changePassword(@RequestBody ChangePasswordRequest request) {
        return accountService.changePassword(request);
    }


    //get current user identity
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('USER')")
    @GetMapping(PRIVATE_API + "/me")
    @ApiOperation(value = "current user", notes = "current user ", response = AccountDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "current user was founded sucessfully"),
            @ApiResponse(code = 400, message = "bad resquest"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    public UserDetails getIdentity() {
        return accountService.getIdentity();

    }

     //  change  password <=============> update by email

    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping(PRIVATE_API + "/resetpasswordtoken/{email}")
    @ApiOperation(value = "Reset Password Token", notes = "reset password", response = AccountDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "reset passwordsucessfully"),
            @ApiResponse(code = 400, message = "bad resquest"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    public ResponseEntity<HttpResponse> resetPasswordToken(@PathVariable("email") String email)  throws MessagingException {
        Account user = accountRepository.findByEmail(email);
        String password = generatePassword();
        user.setPassword(encodePassword(password));
        accountService.save(AccountDTO.fromEntity(user));
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


    /**
     * @param updateProfilRequest
     *
     */
    @RequestMapping(value = PRIVATE_API + "/update-currentUser", method = RequestMethod.PUT)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="update user " ,notes="update  user")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="current user  was assigned Successfully"),
            @ApiResponse(code=400,message="current not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity  updateCUrrentUser (@RequestBody UpdateProfilRequest updateProfilRequest) {

        accountService.updateCUrrentUser(updateProfilRequest.getUsername(),updateProfilRequest.getEmail(),updateProfilRequest.getFiscaleCode());
        return ResponseEntity.ok(new MessageResponse("user updated successfully"));
    }


}


