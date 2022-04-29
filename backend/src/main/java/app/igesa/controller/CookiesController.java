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
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CookiesController {
    @Autowired
    Icookies icookies;

    private static final Logger log = LoggerFactory.getLogger(CookiesController.class);


    @RequestMapping(value="/cookies",method = RequestMethod.POST)
    @ApiOperation(value="ADD Cookies",notes="SAUVGARDER cookies", response = CookiesDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="cookies was saved Successfully"),
            @ApiResponse(code=400,message="cookies not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<CookiesDTO> saveCookies (@RequestBody  CookiesDTO  c) {
        log.debug(" HTTP POST {}",c);
        return new ResponseEntity<> (icookies.saveCookies(c), HttpStatus.CREATED);
    }


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
        return new ResponseEntity<>( icookies.viewCookies(),HttpStatus.OK);
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
        return new ResponseEntity<>(icookies.saveCookies(c),HttpStatus.CREATED);
    }

    @RequestMapping(value="/cookies/{id}",method =RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value="DELETE cookies BY ID ",response = CookiesDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Cookies  was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public void deleteCookies(@PathVariable Long id) {

        log.debug(" HTTP DELETE Cookies BY ID {}",id);

       icookies.deleteCookies(id);
    }



}
