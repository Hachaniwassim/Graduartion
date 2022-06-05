package it.igesa.resources.publicsFO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.dto.FormDTO;
import it.igesa.services.IformEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author  Tarchoun Abir
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "CONTACT-PUBLIC-API")
public class FormPubController {

    @Autowired
    IformEntity iformEntityService;

    private final String PUBLIC_API = "/api/contact-public";

    @RequestMapping(value=PUBLIC_API + "/post-form",method = RequestMethod.POST)
    @ApiOperation(value="ADD FORM ENTITY  ",notes="SAUVGARDER FORM ENTITY ", response = FormDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="FormEntity was saved Successfully"),
            @ApiResponse(code=400,message="FoemEntity not valid"),

    })
    ResponseEntity<FormDTO> save(@RequestBody FormDTO f) {
        return new ResponseEntity<> (iformEntityService.save(f), HttpStatus.CREATED);
    }


    @RequestMapping(value=PUBLIC_API + "/list-form/{id_entreprise}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET A LIST OF FORM ENTITY  ", responseContainer  = "Collection<FormDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="FormEntity was found successfully"),
            @ApiResponse( code=404, message="FormEntity Not found "),
    })
    public ResponseEntity<Collection<FormDTO>> view(@PathVariable Long id_entreprise) {
        return new ResponseEntity<>( iformEntityService.view(id_entreprise),HttpStatus.OK);
    }


    @RequestMapping(value=PUBLIC_API + "/{id}",method =RequestMethod.GET)
    @ApiOperation(value=" GET FORM ENTITY  BY ID ",notes="GET AND SEARCH FOR FORM ENTITY  BY ID ", response = FormDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="FormEntity was found successfully with the provided id"),
            @ApiResponse(code=404,message="No formEntity is found with the provided id ")

    })
    public ResponseEntity<Optional<FormDTO>>findById(@PathVariable Long id) {
        return new ResponseEntity<>( iformEntityService.findById(id),HttpStatus.OK);
    }


}
