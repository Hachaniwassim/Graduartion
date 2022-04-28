package app.igesa.controller;
import app.igesa.dto.MetaDTO;
import app.igesa.metiers.Imeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api( tags = "META")
public class MetaController {

    private static final Logger log = LoggerFactory.getLogger(MetaController.class);

    @Autowired
    private Imeta imetaService;


    @RequestMapping(value="/meta",method = RequestMethod.POST)
    @ApiOperation(value="ADD META",notes="SAUVGARDER META", response = MetaDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="META was saved Successfully"),
            @ApiResponse(code=400,message=" META not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<MetaDTO> save( @RequestBody MetaDTO m) {
        log.debug(" HTTP POST {}",m);
        return new ResponseEntity<> (imetaService.save(m), HttpStatus.CREATED);
    }


    @RequestMapping(value="/meta",method =RequestMethod.GET)
    @ApiOperation(value="GET A LIST OF META", responseContainer  = "Collection<MetaDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Meta was found successfully"),
            @ApiResponse( code=404, message="Meta Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<MetaDTO>> view() {
        log.debug(" HTTP GET ALL META {}");
        return new ResponseEntity<>( imetaService.view(),HttpStatus.OK);
    }


    @RequestMapping(value="/meta/{id}",method =RequestMethod.GET)
    @ApiOperation(value=" GET META BY ID ",notes="GET AND SEARCH FOR META BY ID ", response = MetaDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Meta was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Meta is found with the provided id "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Optional<MetaDTO>> findById(@PathVariable Long id) {
        log.debug(" HTTP GET META BY ID {}",id);
        return new ResponseEntity<>( imetaService.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value="/meta",method =RequestMethod.PUT)
    @ApiOperation(value="UPDATE META ",response = MetaDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Meta was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<MetaDTO> update(@RequestBody MetaDTO m) {
        return new ResponseEntity<>(imetaService.save(m),HttpStatus.CREATED);
    }

    @RequestMapping(value="/meta/{id}",method =RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value="DELETE META BY ID ",response = MetaDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Meta  was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public void delete(@PathVariable Long id) {

        log.debug(" HTTP DELETE META BY ID {}",id);

       imetaService.delete(id);
    }


}
