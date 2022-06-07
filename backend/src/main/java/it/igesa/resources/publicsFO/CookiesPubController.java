package it.igesa.resources.publicsFO;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.dto.legale.CookiesDTO;
import it.igesa.services.Icookies;
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
@Api(tags = "COOKIES-PUBLIC-API")
public class CookiesPubController {
    private final String PUBLIC_API = "/api/cookies-public";
    @Autowired
    Icookies icookies;

    @RequestMapping(value=PUBLIC_API +"/list-cookies/{id_entreprise}",method = RequestMethod.GET)
    @ApiOperation(value="GET Cookies",notes="GET COOKIEES", responseContainer  = "Collection<CookiesDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="cookies was founded Successfully"),
            @ApiResponse(code=400,message="cookies not found"),
    })
    public ResponseEntity<Collection<CookiesDTO>> getCookiesByEntreprise(@PathVariable Long id_entreprise) {

        return new ResponseEntity<>( icookies.getCookiesByEntrepriseId(id_entreprise), HttpStatus.OK);
    }

}
