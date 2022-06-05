package it.igesa.resources;
import it.igesa.dto.TagsDTO;
import it.igesa.services.Itags;
import it.igesa.payload.response.MessageResponse;
import io.swagger.annotations.Api;
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
 * @author Tarchoun Abir
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "TAGS")
public class TagsController {



    /**************************
     *
     *@PRIVATE_API : with token
     *
     ******/
    private final String PRIVATE_API = "api/private/tags";

    private static final Logger log = LoggerFactory.getLogger(TagsController.class);

    @Autowired
    private Itags itagsService ;


    @RequestMapping(value=PRIVATE_API,method = RequestMethod.POST)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="ADD TAGS",notes="SAUVGARDER TAGS", response = TagsDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Tags was saved Successfully"),
            @ApiResponse(code=400,message="Tags not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<TagsDTO> save( @RequestBody TagsDTO tags) {
        return new ResponseEntity<> (itagsService.save(tags) ,HttpStatus.CREATED);
    }


    @RequestMapping(value=PRIVATE_API + "/list-tags/{id_entreprise}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET A LIST OF TAGS", responseContainer  = "Collection<TagsDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Tags was found successfully"),
            @ApiResponse( code=404, message="Tags Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<TagsDTO>>view( @PathVariable Long id_entreprise) {
        log.debug(" HTTP GET ALL TAGS {}");
        return new ResponseEntity<>( itagsService.view(id_entreprise),HttpStatus.OK);
    }

    @RequestMapping(value=PRIVATE_API,method =RequestMethod.PUT)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="UPDATE TAGS BY ID ",response = TagsDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Tags was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<TagsDTO> update(@RequestBody TagsDTO t) {
        return new ResponseEntity<>(itagsService.save(t),HttpStatus.CREATED);
    }

    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.DELETE)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value="DELETE TAGS BY ID ",response = TagsDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Tags was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public ResponseEntity delete(@PathVariable Long id) {

        log.debug(" HTTP DELETE TAGS BY ID {}",id);

        itagsService.delete(id);

        return ResponseEntity.ok(new MessageResponse("deleted  successfully"));
    }


}
