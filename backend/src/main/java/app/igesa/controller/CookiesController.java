package app.igesa.controller;


import app.igesa.dto.CookiesDTO;
import app.igesa.metiers.Icookies;
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
 * @author Tarchoun Abir#
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CookiesController {
    @Autowired
    Icookies icookies;

    private static final Logger log = LoggerFactory.getLogger(CookiesController.class);


    @RequestMapping(value="/cookies",method = RequestMethod.GET)
    @ApiOperation(value="GET Cookies",notes="GET COOKIEES", responseContainer  = "Collection<CookiesDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="cookies was founded Successfully"),
            @ApiResponse(code=400,message="cookies not found"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<CookiesDTO>> viewCookies() {
        log.debug(" HTTP GET ALL cookies {}");
        return new ResponseEntity<>( icookies.getCookies(),HttpStatus.OK);
    }

    @RequestMapping(value="/cookies/{id}",method = RequestMethod.GET)
    @ApiOperation(value="GET COOKIES",notes="GET Cookies", response = CookiesDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="cookies by id was founded Successfully"),
            @ApiResponse(code=400,message="cookies not founded"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })

    public ResponseEntity<CookiesDTO>findById(@PathVariable Long id) {
        log.debug(" HTTP GET COOKIES BY ID {}",id);
        return new ResponseEntity<>(icookies.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value="/cookies",method =RequestMethod.PUT)
    @ApiOperation(value="UPDATE cookies   ",response = CookiesDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="cookies was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<CookiesDTO>updateCookies(@RequestBody CookiesDTO c) {
        return new ResponseEntity<>(icookies.updateCookies(c),HttpStatus.CREATED);
    }


}
