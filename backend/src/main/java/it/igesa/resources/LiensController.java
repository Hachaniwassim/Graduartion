package it.igesa.resources;
import it.igesa.dto.LiensDTO;
import it.igesa.services.implement.LiensImp;
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



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "Liens")
public class LiensController {

 @Autowired
    LiensImp ilensService;

    /**************************
     *
     *@PRIVATE_API : with token
     *
     ******/
    private final String PRIVATE_API = "api/private/liens";
    private static final Logger log = LoggerFactory.getLogger(LiensController.class);




    @RequestMapping(value=PRIVATE_API + "/post-liens",method = RequestMethod.POST)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="ADD liens",notes="SAUVGARDER Liens", response =LiensDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Liens was saved Successfully"),
            @ApiResponse(code=400,message="Liens not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<LiensDTO> save(@RequestBody LiensDTO L) {
        log.debug(" HTTP POST {}",L);
        return new ResponseEntity<> (ilensService.save(L), HttpStatus.CREATED);
    }


    @RequestMapping(value=PRIVATE_API + "/list-liens/{id_entreprise}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET A LIST OF Liens", responseContainer  = "Collection<LiensDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Liens was found successfully"),
            @ApiResponse( code=404, message="Liens Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<LiensDTO>> view(@PathVariable Long id_entreprise) {
        log.debug(" HTTP GET ALL LANGUAGE {}");
        return new ResponseEntity<>(ilensService.view(id_entreprise),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value=" GET Liens BY ID ",notes="GET AND SEARCH FOR LANGUAGE BY ID ", response =LiensDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Liens was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Liens is found with the provided id "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<LiensDTO>findById(@PathVariable Long id) {
        log.debug(" HTTP GET LANGUAGE BY ID {}",id);
        return new ResponseEntity<>(ilensService.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API + "/update-liens",method =RequestMethod.PUT)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="UPDATE LANGUAGE ",response =LiensDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<LiensDTO> update(@RequestBody LiensDTO L ) {
        return new ResponseEntity<>(ilensService.save(L),HttpStatus.CREATED);
    }

    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.DELETE)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value="DELETE LBY ID ",response = LiensDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Liens  was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public ResponseEntity delete(@PathVariable Long id) {
        ilensService.delete(id);
        return new ResponseEntity<>("{code :200 ,msg : deleted successfully}",HttpStatus.OK);
    }
}
