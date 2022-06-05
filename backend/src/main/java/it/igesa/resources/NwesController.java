package it.igesa.resources;


import it.igesa.dto.NewsDTO;
import it.igesa.services.implement.NewsImp;
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
@Api(tags = "Nwes")
public class NwesController {

    @Autowired
    NewsImp inewsService ;

    /**************************
     *
     *@PRIVATE_API : with token
     *
     ******/
    private final String PRIVATE_API = "api/private/nwes";
    private static final Logger log = LoggerFactory.getLogger(NwesController.class);




    @RequestMapping(value=PRIVATE_API + "/post-nwes",method = RequestMethod.POST)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="ADD nwes ",notes="SAUVGARDER nwes", response =NewsDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="nwes was saved Successfully"),
            @ApiResponse(code=400,message="nwes  not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<NewsDTO> save(@RequestBody NewsDTO L) {
        log.debug(" HTTP POST {}",L);
        return new ResponseEntity<> (inewsService.save(L), HttpStatus.CREATED);
    }


    @RequestMapping(value=PRIVATE_API + "/list-nwes/{id_entreprise}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET A LIST OF Liens", responseContainer  = "Collection<NewsDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="nwes was found successfully"),
            @ApiResponse( code=404, message="nwesNot found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<NewsDTO>> view(@PathVariable Long id_entreprise) {
        log.debug(" HTTP GET ALL LANGUAGE {}");
        return new ResponseEntity<>(inewsService.view(id_entreprise),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value=" GET nwes BY ID ",notes="GET AND SEARCH FOR LANGUAGE BY ID ", response =NewsDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="nwes was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Liens is found with the provided id "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<NewsDTO>findById(@PathVariable Long id) {
        log.debug(" HTTP GET LANGUAGE BY ID {}",id);
        return new ResponseEntity<>(inewsService.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API + "/update-nwes",method =RequestMethod.PUT)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="UPDATE LANGUAGE ",response =NewsDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="nwes was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<NewsDTO> update(@RequestBody NewsDTO L ) {
        return new ResponseEntity<>(inewsService.save(L),HttpStatus.CREATED);
    }

    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.DELETE)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value="DELETE LANGUAGE BY ID ",response = NewsDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="nwes  was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public ResponseEntity delete(@PathVariable Long id) {
       inewsService.delete(id);
        return new ResponseEntity<>("{code :200 ,msg : deleted successfully}",HttpStatus.OK);
    }
}
