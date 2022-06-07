package it.igesa.resources.publicsFO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.dto.legale.PrivacyDTO;
import it.igesa.services.Iprivacy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 *
 * @author  Tarchoun Abir
 *
 */


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "PRIVACY-PUBLIC-API")
public class PrivacyPubController {

    private final String PUBLIC_API = "/api/privacy-public";

    @Autowired
    Iprivacy iprivacy;

    @RequestMapping(value = PUBLIC_API + "/list-privacy/{id_entreprise}", method = RequestMethod.GET)
    @ApiOperation(value = "GET PRIVACY", notes = "GET PRIVACY", responseContainer = "Collection<PrivacyDTO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "privacy was founded Successfully"),
            @ApiResponse(code = 400, message = "privacy not found"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),

    })
    public ResponseEntity<Collection<PrivacyDTO>> view(@PathVariable Long id_entreprise) {
        return new ResponseEntity<>(iprivacy.FindByEntrepriseId(id_entreprise), HttpStatus.OK);
    }

}
