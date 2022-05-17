
package app.igesa.controller;
import app.igesa.dto.AccountDTO;
import app.igesa.entity.Account;
import app.igesa.entity.ChangePasswordRequest;
import app.igesa.enumerations.AccountStatus;
import app.igesa.metiers.AccountImp;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;

/**
 * @author Tarchoun Abir#
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AccountController {

    @Autowired
    AccountImp accountImpService ;
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);



    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ApiOperation(value = "ADD User", notes = "SAUVGARDER USER", response = Account.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "user was saved Successfully"),
            @ApiResponse(code = 400, message = "user not valid"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    ResponseEntity<AccountDTO> save(@RequestBody AccountDTO account) {
        log.debug(" HTTP POST {}", account);
        return new ResponseEntity<>(accountImpService.save(account), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ApiOperation(value = "GET A LIST OF USERS", responseContainer = "Collection<Account>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Role  was found successfully"),
            @ApiResponse(code = 404, message = "Role Not found "),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    public ResponseEntity<Collection<AccountDTO>>view() {
        log.debug(" HTTP GET ALL {}");
        return new ResponseEntity<>(accountImpService.findAll(), HttpStatus.OK);
    }


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
        return new ResponseEntity<>(accountImpService.findById(id), HttpStatus.OK);
    }


    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @ApiOperation(value = "UPDATE User BY ID ", response = Account.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was updated successfully"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    public ResponseEntity<AccountDTO> update(@RequestBody AccountDTO user ) {
        return new ResponseEntity<>(accountImpService.save(user),HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "DELETE User BY ID ", response = Account.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = " User was Deleted successfully"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed")

    })
    public ResponseEntity delete(@PathVariable Long id) {
        log.debug(" HTTP DELETE BY ID {}", id);
        accountImpService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/user/toggle-status/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "UPDATE USER BY Status", response = AccountDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "USER Status was updated successfully"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),
    })
    public Account updateSatus(@PathVariable("id")  Long id, @RequestBody AccountStatus status) throws MessagingException {
        return accountImpService.updateSatus(id, status);
    }
    @PostMapping("/user/change-password")
    public boolean changePassword(@RequestBody ChangePasswordRequest request) {
        return accountImpService.changePassword(request);
    }
    @GetMapping("api/auth/me")
    public UserDetails getIdentity() {
        return accountImpService.getIdentity();
    }

}


