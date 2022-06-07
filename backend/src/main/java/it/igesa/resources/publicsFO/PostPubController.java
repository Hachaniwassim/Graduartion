package it.igesa.resources.publicsFO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.dto.PostDTO;
import it.igesa.services.Ipost;
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
@Api(tags = "REFERENCE-CLIENTS-PUBLIC-API")
public class PostPubController {
    @Autowired
    private Ipost ipostService ;
    private final String PUBLIC_API = "/api/Reference-public";


    @RequestMapping(value=PUBLIC_API+ "//{id_entreprise}",method = RequestMethod.GET)
    @ApiOperation(value="GET A LIST OF Post", responseContainer  = "Collection<PostDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Plist-refrenceost was found successfully"),
            @ApiResponse( code=404, message="Post Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<PostDTO>> view(@PathVariable Long id_entreprise) {

        return new ResponseEntity<>( ipostService.view(id_entreprise), HttpStatus.OK);
    }

}
