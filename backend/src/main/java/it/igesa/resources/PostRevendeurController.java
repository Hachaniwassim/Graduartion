package it.igesa.resources;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.dto.DelearsDTO;
import it.igesa.dto.FormDTO;
import it.igesa.dto.GroupeDTO;
import it.igesa.enumerations.RevendeursStatus;
import it.igesa.services.IPostRvendeurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author Tarchoun Abir
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "POST-REVENDEURS")
public class PostRevendeurController {


    private final String PRIVATE_API = "api/private/form-revendeurs";

    @Autowired
    IPostRvendeurs iPostRvendeursService;



    @RequestMapping(value=PRIVATE_API + "/post-form",method = RequestMethod.POST)
    @ApiOperation(value="ADD FORM ENTITY  ",notes="SAUVGARDER FORM ENTITY ", response = DelearsDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Rvendeurs  was saved Successfully"),
            @ApiResponse(code=400,message="Form  not valid"),

    })
    ResponseEntity<DelearsDTO> save(@RequestBody DelearsDTO f) {
        return new ResponseEntity<> (iPostRvendeursService.save(f), HttpStatus.CREATED);
    }


    @RequestMapping(value=PRIVATE_API + "/list-form-revendeurs/{id_entreprise}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET A LIST OF Revendeurs Form ", responseContainer  = "Collection<DelearsDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="FormEntity was found successfully"),

    })
    public ResponseEntity<Collection<DelearsDTO>> view(@PathVariable Long id_entreprise) {

        return new ResponseEntity<>( iPostRvendeursService.view(id_entreprise),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value=" GET FORM ENTITY  BY ID ",notes="GET AND SEARCH FOR FORM ENTITY  BY ID ", response = FormDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="FormEntity was found successfully with the provided id"),
            @ApiResponse(code=404,message="No formEntity is found with the provided id "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Optional<DelearsDTO>>findById(@PathVariable Long id) {
        return new ResponseEntity<>( iPostRvendeursService.findById(id),HttpStatus.OK);
    }


    public ResponseEntity delete(@PathVariable Long id) {
        iPostRvendeursService.delete(id);
        return new ResponseEntity<>("{code :200 ,msg : deleted successfully}",HttpStatus.OK);
    }

    @RequestMapping(value = PRIVATE_API + "/toggle-status/{id}", method = RequestMethod.PUT)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value = "UPDATE GROUPE BY Status ", response = GroupeDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "update revendeurs status successfully"),
    })
    public ResponseEntity<DelearsDTO> updateStatus(@PathVariable("id") long id, @RequestBody RevendeursStatus status) throws MessagingException {
        return new ResponseEntity<>(iPostRvendeursService.updateStatusRevendeur(id, status), HttpStatus.CREATED);
    }

}
