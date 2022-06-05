package it.igesa.resources.publicsFO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.dto.CategoryDTO;
import it.igesa.services.Icategory;
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
@Api(tags = "CATEGORY-PUBLIC-API")
public class CategoriePubController {

    /*@RequestHeader(value = Consts.ENTERPRISE_ID, required = false)*/

    private final String PUBLIC_API = "/api/category-public";

    @Autowired
    private Icategory categoryService;

@RequestMapping(value=PUBLIC_API + "/list-category/{enterprise_id}",method =RequestMethod.GET)
@ApiOperation(value="GET A LIST OF CATEGORY ", responseContainer  = "Collection<CategoryDTO")
@ApiResponses(value= {
        @ApiResponse(code=200,message="Category was found successfully")

})
public ResponseEntity<Collection<CategoryDTO>> view( @PathVariable  Long enterprise_id) {
    return new ResponseEntity<>( categoryService.getAllByEntreprise(enterprise_id),HttpStatus.OK);
}


    @RequestMapping(value=PUBLIC_API  + "/{id}",method =RequestMethod.GET)
    @ApiOperation(value=" GET CATEGORY BY ID ",notes="GET AND SEARCH FOR CATEGORY BY ID ", response = CategoryDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Category was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Category is found with the provided id ")

    })
    public ResponseEntity <CategoryDTO> findById(@PathVariable Long id) {

        return new ResponseEntity<>( categoryService.findById(id),HttpStatus.OK);
    }
}
