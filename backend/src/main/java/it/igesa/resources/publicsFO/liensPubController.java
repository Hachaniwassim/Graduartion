package it.igesa.resources.publicsFO;

import it.igesa.dto.LiensDTO;
import it.igesa.services.Iliens;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "links-PUBLIC-API")
public class liensPubController {

    private final String PUBLIC_API = "/api/links-public";

 @Autowired
    Iliens ilienService;

    @RequestMapping(value=PUBLIC_API + "/list-liens/{id_entreprise}",method =RequestMethod.GET)
    @ApiOperation(value="GET A LIST OF Liens", responseContainer  = "Collection<LiensDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Liens was found successfully"),
            @ApiResponse( code=404, message="Liens Not found "),

    })
        public ResponseEntity<Collection<LiensDTO>> view(/*@RequestHeader(value = Consts.ENTERPRISE_ID, required = false)*/@PathVariable Long id_entreprise) {
        System.out.println("=====================================>"+id_entreprise);
        return new ResponseEntity<>(ilienService.view(id_entreprise) ,HttpStatus.OK);
    }


    @RequestMapping(value=PUBLIC_API + "/{id}",method =RequestMethod.GET)
    @ApiOperation(value=" GET Liens BY ID ",notes="GET AND SEARCH FOR LANGUAGE BY ID ", response =LiensDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Liens was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Liens is found with the provided id "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<LiensDTO>findById(@PathVariable Long id) {
        return new ResponseEntity<>(ilienService.findById(id),HttpStatus.OK);
    }



}
