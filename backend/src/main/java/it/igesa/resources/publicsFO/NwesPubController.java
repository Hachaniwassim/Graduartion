package it.igesa.resources.publicsFO;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.dto.NewsDTO;
import it.igesa.services.implement.NewsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 *
 * @author  Tarchoun Abir
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "NWES-PUBLIC-API")
public class NwesPubController {


    @Autowired
    NewsImp inewsService;
    private final String PUBLIC_API = "/api/nwes-public";


    @RequestMapping(value = PUBLIC_API + "/list-nwes/{id_entreprise}", method = RequestMethod.GET)
    @ApiOperation(value = "GET A LIST OF Liens", responseContainer = "Collection<NewsDTO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "nwes was found successfully"),
            @ApiResponse(code = 404, message = "nwesNot found "),
    })
    public ResponseEntity<Collection<NewsDTO>> view(@PathVariable Long id_entreprise) {

        return new ResponseEntity<>(inewsService.view(id_entreprise), HttpStatus.OK);
    }



    @RequestMapping(value=PUBLIC_API + "/{id}",method =RequestMethod.GET)
    @ApiOperation(value="GET nwes BY ID", response =NewsDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="nwes was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Liens is found with the provided id "),

    })
    public ResponseEntity<NewsDTO>findById(@PathVariable Long id) {
        return new ResponseEntity<>(inewsService.findById(id),HttpStatus.OK);
    }



}
