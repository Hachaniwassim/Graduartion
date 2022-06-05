package it.igesa.resources.publicsFO;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.dto.TagsDTO;
import it.igesa.services.Itags;
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
@Api(tags = "TAGS-PUBLIC-API")
public class TagsPubController {
    private final String PUBLIC_API = "/api/tags-public";

    @Autowired
    private Itags itagsService ;

    @RequestMapping(value=PUBLIC_API + "/list-tags/{id_entreprise}",method = RequestMethod.GET)
    @ApiOperation(value="GET A LIST OF TAGS", responseContainer  = "Collection<TagsDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Tags was found successfully"),
            @ApiResponse( code=404, message="Tags Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<TagsDTO>> view(@PathVariable Long id_entreprise) {

        return new ResponseEntity<>( itagsService.view(id_entreprise), HttpStatus.OK);
    }


}
