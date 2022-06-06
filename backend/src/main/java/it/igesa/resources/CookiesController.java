package it.igesa.resources;
import it.igesa.dto.legale.CookiesDTO;
import it.igesa.services.Icookies;
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

/**
 *
 * @author Tarchoun Abir
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CookiesController {

    /**************************
     *
     *@PRIVATE_API : with token
     *
     ******/
    private final String PRIVATE_API = "/api/private/cookies";

    /** logger for debug : warning : success **/
    private static final Logger log = LoggerFactory.getLogger(CookiesController.class);

    @Autowired
    Icookies icookies;


    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value=PRIVATE_API + "/post-cookies",method =RequestMethod.POST)
    @ApiOperation(value="UPDATE cookies   ",response = CookiesDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="cookies was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<CookiesDTO>updateByEntreprise(@RequestBody CookiesDTO c) {
        return new ResponseEntity<>(icookies.updateCookies(c),HttpStatus.CREATED);
    }


    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value=PRIVATE_API + "/update",method =RequestMethod.PUT)

    @ApiOperation(value="UPDATE cookies   ",response = CookiesDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="cookies was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<CookiesDTO>updateCookiesByEntreprise(@RequestBody CookiesDTO c) {
        return new ResponseEntity<>(icookies.updateCookies(c),HttpStatus.CREATED);
    }

    /****
     *
     * @param id_entreprise
     * @return
     */

    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value=PRIVATE_API+"/list-cookies/{id_entreprise}",method = RequestMethod.GET)
    @ApiOperation(value="GET Cookies",notes="GET COOKIEES", responseContainer  = "Collection<CookiesDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="cookies was founded Successfully"),
            @ApiResponse(code=400,message="cookies not found"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<CookiesDTO>> getCookiesByEntreprise(@PathVariable Long id_entreprise) {

        return new ResponseEntity<>( icookies.getCookiesByEntrepriseId(id_entreprise),HttpStatus.OK);
    }

    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value=PRIVATE_API+"/{id}",method = RequestMethod.GET)
    @ApiOperation(value="GET Cookies",notes="GET COOKIEES", response = CookiesDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="cookies was founded Successfully"),
            @ApiResponse(code=400,message="cookies not found"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })

    public ResponseEntity<CookiesDTO>findById(@PathVariable Long id) {
        log.debug(" HTTP GET COOKIES BY ID {}",id);
        return new ResponseEntity<>(icookies.findById(id),HttpStatus.OK);
    }


}
