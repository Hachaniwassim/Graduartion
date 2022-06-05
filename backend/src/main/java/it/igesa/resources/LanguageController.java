package it.igesa.resources;
import it.igesa.dto.LanguageDTO;
import it.igesa.services.Ilanguage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Wassim Hachani
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "LANGUAGE")
public class LanguageController {



    /********************
     *
     * @Api  PRIVATE_API : with token
     *
     *****/

    private final String PRIVATE_API = "api/private/languages";


    @Autowired
    Ilanguage ilanguageService ;

    private static final Logger log = LoggerFactory.getLogger(LanguageController.class);




    @RequestMapping(value=PRIVATE_API + "/post-language",method = RequestMethod.POST)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="ADD LANGUAGE",notes="SAUVGARDER LANGUAGE", response = LanguageDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Language was saved Successfully"),
            @ApiResponse(code=400,message="Language not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<LanguageDTO> save(@RequestBody LanguageDTO L) {
        log.debug(" HTTP POST {}",L);
        return new ResponseEntity<> (ilanguageService.save(L), HttpStatus.CREATED);
    }


    @RequestMapping(value=PRIVATE_API + "/list-language/{id_entreprise}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET A LIST OF LANGUAGE", responseContainer  = "Collection<LanguageDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Language was found successfully"),
            @ApiResponse( code=404, message="Language Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<LanguageDTO>> view(@PathVariable Long id_entreprise) {
        log.debug(" HTTP GET ALL LANGUAGE {}");
        return new ResponseEntity<>(ilanguageService.view(id_entreprise),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value=" GET LANGUAGE BY ID ",notes="GET AND SEARCH FOR LANGUAGE BY ID ", response = LanguageDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Language was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Language is found with the provided id "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<LanguageDTO>findById(@PathVariable Long id) {
        log.debug(" HTTP GET LANGUAGE BY ID {}",id);
        return new ResponseEntity<>(ilanguageService.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API + "/update-language",method =RequestMethod.PUT)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="UPDATE LANGUAGE ",response = LanguageDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Language was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<LanguageDTO> update(@RequestBody LanguageDTO L ) {
        return new ResponseEntity<>(ilanguageService.save(L),HttpStatus.CREATED);
    }

    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.DELETE)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value="DELETE LANGUAGE BY ID ",response = LanguageDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Language was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public ResponseEntity delete(@PathVariable Long id) {
        log.debug(" HTTP DELETE Language BY ID {}",id);
        ilanguageService.delete(id);
        return new ResponseEntity<>("{code :200 ,msg : deleted successfully}",HttpStatus.OK);
    }

}
