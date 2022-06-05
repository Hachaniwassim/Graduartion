package it.igesa.resources;
import it.igesa.dto.RoleDTO;
import it.igesa.domaine.Role;
import it.igesa.services.Irole;
import it.igesa.payload.request.UpdateRoleRequest;
import it.igesa.payload.response.MessageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Wassim Hachani
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "ROLE")
public class RoleController {

    /***************
     *
     * @Api PRIVATE_API : with token
     *
     ********/

    private final String PRIVATE_API = "/api/private/roles";
    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private Irole iroleService;



    @RequestMapping(value = PRIVATE_API, method = RequestMethod.POST)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value = "ADD ROLE", notes = "SAUVGARDER ROLE", response = RoleDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Role was saved Successfully"),
            @ApiResponse(code = 400, message = "Role not valid"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    ResponseEntity<RoleDTO> save(@RequestBody RoleDTO r) {
        log.debug(" HTTP POST {}", r);
        return new ResponseEntity<>(iroleService.save(r), HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value = PRIVATE_API, method = RequestMethod.GET)
    @ApiOperation(value = "GET A LIST OF ROLE", responseContainer = "Collection<RoleDTO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Role  was found successfully"),
            @ApiResponse(code = 404, message = "Role Not found "),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    public ResponseEntity<List<RoleDTO>> view() {
        System.out.println(" HTTP GET ALL ROLE {}");
        return new ResponseEntity<>(iroleService.view(), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value = PRIVATE_API + "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = " GET ROLE  BY ID ", notes = "GET AND SEARCH FOR ROLE  BY ID ", response = RoleDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Role was found successfully with the provided id"),
            @ApiResponse(code = 404, message = "No Role is found with the provided id "),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    public ResponseEntity<RoleDTO> findById(@PathVariable Long id) {
        log.debug(" HTTP GET Role  BY ID {}", id);
        return new ResponseEntity<>(iroleService.findById(id), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value = PRIVATE_API + "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "DELETE ROLE BY ID ", response = Role.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Role was Deleted successfully"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed")

    })
    public ResponseEntity delete(@PathVariable Long id) {
        log.debug(" HTTP DELETE ROLE BY ID {}", id);
        iroleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value = PRIVATE_API + "/update-role", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "DELETE ROLE BY ID ", response = RoleDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Role was Deleted successfully"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),
            @ApiResponse(code = 403, message = "not permitted or allowed")

    })
    public ResponseEntity updateRole(@RequestBody UpdateRoleRequest updateRole) {


        iroleService.updateRole(updateRole.getId_role(), updateRole.getId_account());

        return ResponseEntity.ok(new MessageResponse("Role updated successfully"));
    }
}
