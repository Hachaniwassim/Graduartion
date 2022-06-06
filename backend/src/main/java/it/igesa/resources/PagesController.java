package it.igesa.resources;

import it.igesa.dto.pages.PageDTO;
import it.igesa.services.Ipages;
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
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Wassim Hachani
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "PAGES")
public class PagesController {



    /**************************
     *
     *@PRIVATE_API : with token
     *@PRIVATE_API : with token
     *
     ******/
    private final String PRIVATE_API = "api/private/pages";
    private static final Logger log = LoggerFactory.getLogger(PagesController.class);
    @Autowired
    private Ipages ipagesService;

    @RequestMapping(value=PRIVATE_API + "/post-revendeur",method = RequestMethod.POST)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="ADD PAGE",notes="SAUVGARDER PAGE", response = PageDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Page was saved Successfully"),
            @ApiResponse(code=400,message="Page not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="Not permitted or allowed"),

    })
    ResponseEntity<PageDTO> save( @RequestBody PageDTO p) {
        log.debug(" HTTP POST {}",p);
        return new ResponseEntity<> (ipagesService.save(p), HttpStatus.CREATED);
    }


    @RequestMapping(value=PRIVATE_API + "/list-pages-revendeur/{id_enterprise}" ,method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET A LIST OF PAGE ", responseContainer  = "Collection<PageDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Page was found successfully"),
            @ApiResponse( code=404, message="Page Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="Not permitted or allowed"),

    })
    public ResponseEntity<Collection<PageDTO>> view( @PathVariable Long id_enterprise) {
        log.debug(" HTTP GET ALL PAGES {}");
        return new ResponseEntity<>( ipagesService.view(id_enterprise),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value=" GET PAGEBY ID ",notes="GET AND SEARCH FOR PAGE BY ID ", response = PageDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Page was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Page is found with the provided id "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Optional<PageDTO>>findById(@PathVariable Long id) {
        log.debug(" HTTP GET PAGE BY ID {}",id);
        return new ResponseEntity<>( ipagesService.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API ,method =RequestMethod.PUT)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="UPDATE PAGE  ",response = PageDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Page was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<PageDTO> update( @RequestBody PageDTO p) {
        return new ResponseEntity<>(ipagesService.save(p),HttpStatus.CREATED);
    }

    @RequestMapping(value= PRIVATE_API  + "/{id}",method =RequestMethod.DELETE)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value="DELETE PAGE BY ID ",response = PageDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Page was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public ResponseEntity delete(@PathVariable Long id) {

        log.debug(" HTTP DELETE PAGE BY ID {}",id);

        ipagesService.delete(id);

        return new ResponseEntity<>("{code :200 ,msg : deleted successfully}",HttpStatus.OK);
    }

}
