package app.igesa.controller;

import app.igesa.dto.ConfigGeneralDTO;
import app.igesa.metiers.Iconfiguration;
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
import java.util.Locale;
import java.util.Optional;

/**
 *
 * @author Tarchoun Abir
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "CONFIGURATION")
public class ConfigurationController {

    /**************************
     *
     *@PRIVATE_API : with token
     *
     ******/
    private final String PRIVATE_API = "/api/private/config";

   /** logger for : debug , warning , success **/
    private static final Logger log = LoggerFactory.getLogger(ConfigurationController.class);
    @Autowired
    private Iconfiguration iconfigurationService ;


    @RequestMapping(value=PRIVATE_API ,method = RequestMethod.POST)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="ADD CONFIGURATION",notes="SAUVGARDER CONFIGURATION", response = ConfigGeneralDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Configuration was saved Successfully"),
            @ApiResponse(code=400,message="Configuration not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<ConfigGeneralDTO> save(@RequestBody ConfigGeneralDTO c) {
        log.debug(" HTTP POST {}",c);
        return new ResponseEntity<> (iconfigurationService.save(c), HttpStatus.CREATED);
    }


    @RequestMapping(value=PRIVATE_API ,method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET CONFIGURATION GENERALE", responseContainer  = "Collection<ConfigGeneralDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Configuration was found successfully"),
            @ApiResponse( code=404, message="Configuration Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<ConfigGeneralDTO>> view() {
        log.debug(" HTTP GET CONFIG {}");
        return new ResponseEntity<>( iconfigurationService.view(),HttpStatus.OK);
    }



    @RequestMapping(value=PRIVATE_API  + "/{id}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value=" GET CONFIG BY ID ",notes="GET AND SEARCH FOR CONFIG BY ID ", response = ConfigGeneralDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Config was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Config is found with the provided id "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })

    public ResponseEntity<ConfigGeneralDTO>findById(@PathVariable Long id) {
        log.debug(" HTTP GET CONFIG BY ID {}",id);
        return new ResponseEntity<>( iconfigurationService.findById(id),HttpStatus.OK);
    }


    //@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value=PRIVATE_API ,method =RequestMethod.PUT)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="UPDATE CONFIG  ",response = ConfigGeneralDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Config was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })

    public ResponseEntity<ConfigGeneralDTO> update(@RequestBody ConfigGeneralDTO c) {
        return new ResponseEntity<>(iconfigurationService.save(c),HttpStatus.CREATED);
    }

    @RequestMapping(value=PRIVATE_API  + "/{id}",method =RequestMethod.DELETE)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value="DELETE CONFIGURATION BY ID ",response = ConfigGeneralDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Config was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })

    public void delete(@PathVariable Long id) {

        log.debug(" HTTP DELETE CONFIG BY ID {}",id);

        iconfigurationService.delete(id);
    }


}
