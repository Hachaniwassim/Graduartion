package it.igesa.resources;
import it.igesa.dto.legale.PrivacyDTO;
import it.igesa.services.Iprivacy;
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
import java.util.Collection;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PrivacyController {

    private final String PRIVATE_API = "api/private/privacy";
    private static final Logger log = LoggerFactory.getLogger(PrivacyController.class);


    @Autowired
    Iprivacy iprivacy;


    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value = PRIVATE_API + "/post-privacy", method = RequestMethod.POST)
    @ApiOperation(value = "ADD PRIVACY", notes = "SAUVGARDER PRIVACY", response = PrivacyDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "privacy was saved Successfully"),
            @ApiResponse(code = 400, message = "privacy not valid"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    ResponseEntity<PrivacyDTO> save(@RequestBody PrivacyDTO p) {
        log.debug(" HTTP POST {}", p);
        return new ResponseEntity<>(iprivacy.updateByEntreprise(p), HttpStatus.CREATED);
    }


    @RequestMapping(value = PRIVATE_API + "/list-privacy/{id_entreprise}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value = "GET PRIVACY", notes = "GET PRIVACY", responseContainer = "Collection<PrivacyDTO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "privacy was founded Successfully"),
            @ApiResponse(code = 400, message = "privacy not found"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    public ResponseEntity<Collection<PrivacyDTO>> view( @PathVariable Long id_entreprise) {
        log.debug(" HTTP GET ALL privacy  {}");
        return new ResponseEntity<>(iprivacy.FindByEntrepriseId(id_entreprise), HttpStatus.OK);
    }

    @RequestMapping(value = PRIVATE_API + "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value = "GET PRIVACY", notes = "GET PRIVACY", response = PrivacyDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "privacy by id was founded Successfully"),
            @ApiResponse(code = 400, message = "privacy not founded"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    public ResponseEntity<PrivacyDTO> findById(@PathVariable Long id) {
        log.debug(" HTTP GET PRIVACY  BY ID {}", id);
        return new ResponseEntity<>(iprivacy.findById(id), HttpStatus.OK);
    }


    @RequestMapping(value = PRIVATE_API + "/update-privacy", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value = "UPDATE privacy  ", response = PrivacyDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "privacy was updated successfully"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    public ResponseEntity<PrivacyDTO> update(@RequestBody PrivacyDTO c) {

        return new ResponseEntity<>(iprivacy.updateByEntreprise(c), HttpStatus.CREATED);
    }

    @RequestMapping(value = PRIVATE_API + "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value = "DELETE PRIVACY BY ID ", response = PrivacyDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Privacy was Deleted successfully"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed")

    })
    public ResponseEntity delete(@PathVariable Long id) {

        log.debug(" HTTP DELETE Privacy BY ID {}", id);

        iprivacy.delete(id);

        return new ResponseEntity<>("{code :200 ,msg : deleted successfully}",HttpStatus.OK);
    }


}