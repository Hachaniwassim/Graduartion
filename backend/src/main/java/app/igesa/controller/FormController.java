package app.igesa.controller;

import app.igesa.dto.EntrepriseDTO;
import app.igesa.dto.FormDTO;
import app.igesa.metiers.IformEntity;
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
import java.util.Optional;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(tags = "FORMENTITY")
public class FormController {

    @Autowired
    IformEntity iformEntityService;

    private static final Logger log = LoggerFactory.getLogger(FormController.class);


    @RequestMapping(value="/form",method = RequestMethod.POST)
    @ApiOperation(value="ADD FORM ENTITY  ",notes="SAUVGARDER FORM ENTITY ", response = FormDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="FormEntity was saved Successfully"),
            @ApiResponse(code=400,message="FoemEntity not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<FormDTO> save( @RequestBody FormDTO f) {
        log.debug(" HTTP POST {}",f);
        return new ResponseEntity<> (iformEntityService.save(f), HttpStatus.CREATED);
    }


    @RequestMapping(value="/form",method =RequestMethod.GET)
    @ApiOperation(value="GET A LIST OF FORM ENTITY  ", responseContainer  = "Collection<FormDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="FormEntity was found successfully"),
            @ApiResponse( code=404, message="FormEntity Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<FormDTO>> view() {
        log.debug(" HTTP GET ALL FORM ENTITY   {}");
        return new ResponseEntity<>( iformEntityService.view(),HttpStatus.OK);
    }


    @RequestMapping(value="/form/{id}",method =RequestMethod.GET)
    @ApiOperation(value=" GET FORM ENTITY  BY ID ",notes="GET AND SEARCH FOR FORM ENTITY  BY ID ", response = FormDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="FormEntity was found successfully with the provided id"),
            @ApiResponse(code=404,message="No formEntity is found with the provided id "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Optional<FormDTO>>findById(@PathVariable Long id) {
        log.debug(" HTTP GET FORM ENTITY   BY ID {}",id);
        return new ResponseEntity<>( iformEntityService.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value="/form",method =RequestMethod.PUT)
    @ApiOperation(value="UPDATE FORM ENTITY ",response = FormDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="FormEntity was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<FormDTO> update(@RequestBody FormDTO f) {
        return new ResponseEntity<>(iformEntityService.save(f),HttpStatus.CREATED);
    }

    @RequestMapping(value="/form/{id}",method =RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value="DELETE FORM ENTITY BY ID ",response = EntrepriseDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message=" Formentity  was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public void delete(@PathVariable Long id) {

        log.debug(" HTTP DELETE FORM ENTITY   BY ID {}",id);

     iformEntityService.delete(id);
    }

}
