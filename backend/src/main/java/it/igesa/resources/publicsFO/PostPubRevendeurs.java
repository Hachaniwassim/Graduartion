package it.igesa.resources.publicsFO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.dto.DelearsDTO;
import it.igesa.services.IPostRvendeurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


/**
 *
 * @author  Tarchoun Abir
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "REVENDEURS-PUBLIC-API")
public class PostPubRevendeurs {
    @Autowired
    IPostRvendeurs iPostRvendeursService ;
    private final String PUBLIC_API = "/api/post-revendeurs-public";

    @RequestMapping(value=PUBLIC_API + "/post-form",method = RequestMethod.POST)
    @ApiOperation(value="ADD FORM ENTITY  ",notes="SAUVGARDER FORM ENTITY ", response = DelearsDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Rvendeurs  was saved Successfully"),
            @ApiResponse(code=400,message="Form  not valid"),

    })
    ResponseEntity<DelearsDTO> save(@RequestBody DelearsDTO f) {
        return new ResponseEntity<> (iPostRvendeursService.save(f), HttpStatus.CREATED);
    }


    @RequestMapping(value=PUBLIC_API + "/list-form-revendeurs/{id_entreprise}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET A LIST OF Revendeurs Form ", responseContainer  = "Collection<DelearsDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="FormEntity was found successfully"),

    })
    public ResponseEntity<Collection<DelearsDTO>> view(@PathVariable Long id_entreprise) {

        return new ResponseEntity<>( iPostRvendeursService.view(id_entreprise),HttpStatus.OK);
    }



}
