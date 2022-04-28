package app.igesa.controller;


import app.igesa.dto.CompanyBusinessDTO;
import app.igesa.dto.PrivacyDTO;
import app.igesa.metiers.Iprivacy;
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
public class PrivacyController {
    @Autowired
    Iprivacy iprivacy;

    private static final Logger log = LoggerFactory.getLogger(PrivacyController.class);


    @RequestMapping(value="/privacy",method = RequestMethod.POST)
    @ApiOperation(value="ADD PRIVACY",notes="SAUVGARDER PRIVACY", response = PrivacyDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="privacy was saved Successfully"),
            @ApiResponse(code=400,message="privacy not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<PrivacyDTO> save(@RequestBody PrivacyDTO p) {
        log.debug(" HTTP POST {}",p);
        return new ResponseEntity<> (iprivacy.save(p), HttpStatus.CREATED);
    }


    @RequestMapping(value="/privacy",method = RequestMethod.GET)
    @ApiOperation(value="GET PRIVACY",notes="GETPRIVACY", responseContainer  = "Collection<PrivacyDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="privacy was founded Successfully"),
            @ApiResponse(code=400,message="privacy not found"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<PrivacyDTO>> view() {
        log.debug(" HTTP GET ALL privacy  {}");
        return new ResponseEntity<>( iprivacy.view(),HttpStatus.OK);
    }

    @RequestMapping(value="/privacy/{id}",method = RequestMethod.GET)
    @ApiOperation(value="GET PRIVACY",notes="GET PRIVACY", response = PrivacyDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="privacy by id was founded Successfully"),
            @ApiResponse(code=400,message="privacy not founded"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<PrivacyDTO>findById(@PathVariable Long id) {
        log.debug(" HTTP GET PRIVACY  BY ID {}",id);
        return new ResponseEntity<>(iprivacy.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value="/privacy",method =RequestMethod.PUT)
    @ApiOperation(value="UPDATE privacy  ",response = PrivacyDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="privacy was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<PrivacyDTO>update(@RequestBody PrivacyDTO c) {
        return new ResponseEntity<>(iprivacy.save(c),HttpStatus.CREATED);
    }

    @RequestMapping(value="/privacy/{id}",method =RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value="DELETE PRIVACY BY ID ",response = PrivacyDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Privacy was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public void delete(@PathVariable Long id) {

        log.debug(" HTTP DELETE Privacy BY ID {}",id);

        iprivacy.delete(id);
    }



}
