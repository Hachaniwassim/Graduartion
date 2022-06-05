package it.igesa.resources;
import it.igesa.dto.PostDTO;
import it.igesa.services.Ipost;
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
 * @author Tarchoun Abir
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "POST")
public class PostController {



    /**************************
     *
     *@PRIVATE_API : with token
     *
     ******/
    private final String PRIVATE_API = "api/private/logo-clients";

    @Autowired
    private Ipost ipostService ;
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @RequestMapping(value=PRIVATE_API + "/post-logo-clients",method = RequestMethod.POST)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="ADD POST" ,notes="SAUVGARDER POST", response = PostDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Post was saved Successfully"),
            @ApiResponse(code=400,message="Post not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<PostDTO> save(@RequestBody PostDTO p) {
        log.debug(" HTTP POST {}",p);
        return new ResponseEntity<> (ipostService.save(p), HttpStatus.CREATED);
    }


    @RequestMapping(value=PRIVATE_API + "/list-post/{id_entreprise}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET A LIST OF Post", responseContainer  = "Collection<PostDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Post was found successfully"),
            @ApiResponse( code=404, message="Post Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<PostDTO>> view(@PathVariable Long id_entreprise) {
        log.debug(" HTTP GET ALL Post {}");
        return new ResponseEntity<>( ipostService.view(id_entreprise),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value=" GET POST BY ID ",notes="GET AND SEARCH FOR POST BY ID ", response = PostDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Post was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Post is found with the provided id "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<PostDTO>findById(@PathVariable Long id) {
        log.debug(" HTTP GET POST BY ID {}",id);
        return new ResponseEntity<>( ipostService.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API + "/update-logo-clients",method =RequestMethod.PUT)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="UPDATE Post ",response = PostDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Post was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<PostDTO> update(@PathVariable Long id, @RequestBody PostDTO p) {
        return new ResponseEntity<>(ipostService.save(p),HttpStatus.CREATED);
    }

    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.DELETE)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value="DELETE POST BY ID ",response = PostDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Post was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public ResponseEntity delete(@PathVariable Long id) {

        log.debug(" HTTP DELETE POST BY ID {}",id);
        ipostService.delete(id);
        return new ResponseEntity<>("{code :200 ,msg : deleted successfully}",HttpStatus.OK);

    }
}
