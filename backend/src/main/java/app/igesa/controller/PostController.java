package app.igesa.controller;
import app.igesa.dto.PostDTO;
import app.igesa.metiers.Ipost;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(tags = "POST")
public class PostController {
    @Autowired
    private Ipost ipostService ;
    private static final Logger log = LoggerFactory.getLogger(PostController.class);


    @RequestMapping(value="/post",method = RequestMethod.POST)
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


    @RequestMapping(value="/post",method =RequestMethod.GET)
    @ApiOperation(value="GET A LIST OF Post", responseContainer  = "Collection<PostDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Post was found successfully"),
            @ApiResponse( code=404, message="Post Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<PostDTO>> view() {
        log.debug(" HTTP GET ALL Post {}");
        return new ResponseEntity<>( ipostService.view(),HttpStatus.OK);
    }


    @RequestMapping(value="/post/{id}",method =RequestMethod.GET)
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


    @RequestMapping(value="/post",method =RequestMethod.PUT)
    @ApiOperation(value="UPDATE Post ",response = PostDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Post was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<PostDTO> update(@PathVariable Long id, @RequestBody PostDTO p) {
        return new ResponseEntity<>(ipostService.save(p),HttpStatus.CREATED);
    }

    @RequestMapping(value="/post/{id}",method =RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value="DELETE POST BY ID ",response = PostDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Post was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public void delete(@PathVariable Long id) {

        log.debug(" HTTP DELETE POST BY ID {}",id);

       ipostService.delete(id);
    }
}
