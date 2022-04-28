package app.igesa.controller;
import app.igesa.dto.LanguageDTO;
import app.igesa.metiers.Ilanguage;
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
@Api(tags = "LANGUAGE")
public class LanguageController {
    @Autowired
    Ilanguage ilanguageService ;

    private static final Logger log = LoggerFactory.getLogger(LanguageController.class);




    @RequestMapping(value="/lang",method = RequestMethod.POST)
    @ApiOperation(value="ADD LANGUAGE",notes="SAUVGARDER LANGUAGE", response = LanguageDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Language was saved Successfully"),
            @ApiResponse(code=400,message="Language not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<LanguageDTO> save(@RequestBody LanguageDTO L) {
        log.debug(" HTTP POST {}",L);
        return new ResponseEntity<> (ilanguageService.save(L), HttpStatus.CREATED);
    }


    @RequestMapping(value="/lang",method =RequestMethod.GET)
    @ApiOperation(value="GET A LIST OF LANGUAGE", responseContainer  = "Collection<LanguageDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Language was found successfully"),
            @ApiResponse( code=404, message="Language Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<LanguageDTO>> view() {
        log.debug(" HTTP GET ALL LANGUAGE {}");
        return new ResponseEntity<>(ilanguageService.view(),HttpStatus.OK);
    }


    @RequestMapping(value="/lang/{id}",method =RequestMethod.GET)
    @ApiOperation(value=" GET LANGUAGE BY ID ",notes="GET AND SEARCH FOR LANGUAGE BY ID ", response = LanguageDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Language was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Language is found with the provided id "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<LanguageDTO>findById(@PathVariable Long id) {
        log.debug(" HTTP GET LANGUAGE BY ID {}",id);
        return new ResponseEntity<>(ilanguageService.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value="/lang",method =RequestMethod.PUT)
    @ApiOperation(value="UPDATE LANGUAGE ",response = LanguageDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Language was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<LanguageDTO> update(@RequestBody LanguageDTO L ) {
        return new ResponseEntity<>(ilanguageService.save(L),HttpStatus.CREATED);
    }

    @RequestMapping(value="/lang/{id}",method =RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value="DELETE LANGUAGE BY ID ",response = LanguageDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Language was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public void delete(@PathVariable Long id) {

        log.debug(" HTTP DELETE Language BY ID {}",id);

        ilanguageService.delete(id);
    }

}
