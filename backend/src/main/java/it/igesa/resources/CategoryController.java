package it.igesa.resources;

import it.igesa.dto.CategoryDTO;
import it.igesa.services.Icategory;
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

/**
 *
 * @author Wassim Hachani
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "CATEGORY")
public class CategoryController {
    private static final Logger log = LoggerFactory.getLogger(EntrepriseController.class);

    /**************************
     *
     *@PRIVATE_API : with token
     *
     ******/
    private final String PRIVATE_API = "/api/private/category";

    @Autowired
    private Icategory categoryService ;



    @RequestMapping(value=PRIVATE_API + "/post-category",method = RequestMethod.POST)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="ADD CATEGORY",notes="SAUVGARDER CATEGORY", response = CategoryDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Category  was saved Successfully"),
            @ApiResponse(code=400,message="Category not valid")

    })
    ResponseEntity<CategoryDTO> save( @RequestBody CategoryDTO c) {
        log.debug(" HTTP POST {}",c);
        return new ResponseEntity<> (categoryService.save(c), HttpStatus.CREATED);
    }


    @RequestMapping(value=PRIVATE_API + "/list-category/{enterprise_id}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET A LIST OF CATEGORY ", responseContainer  = "Collection<CategoryDTO")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Category was found successfully")

    })
    public ResponseEntity<Collection<CategoryDTO>> view( @PathVariable  Long enterprise_id) {
        log.debug(" HTTP GET ALL Category {}");
        return new ResponseEntity<>( categoryService.getAllByEntreprise(enterprise_id),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value=" GET CATEGORY BY ID ",notes="GET AND SEARCH FOR CATEGORY BY ID ", response = CategoryDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Category was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Category is found with the provided id ")

    })
    public ResponseEntity <CategoryDTO> findById(@PathVariable Long id) {
        log.debug(" HTTP GET Category BY ID {}",id);
        return new ResponseEntity<>( categoryService.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API + "/update-category",method =RequestMethod.PUT)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="UPDATE CATEGORY ",response = CategoryDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Category was updated successfully")

    })
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO c ) {
        return new ResponseEntity<>(categoryService.save(c),HttpStatus.CREATED);
    }

    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.DELETE)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value="DELETE CATEGORY BY ID ",response = CategoryDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Category was Deleted successfully")

    })

    public ResponseEntity delete(@PathVariable Long id) {

        log.debug(" HTTP DELETE CATEGORY BY ID {}",id);

        categoryService.delete(id);

        return new ResponseEntity<>("{code :200 ,msg : deleted successfully}",HttpStatus.OK);
    }

    @RequestMapping(value=PRIVATE_API +"/images/{id}",method =RequestMethod.DELETE)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value="DELETE CATEGORY BY ID and type ",response = CategoryDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Category was Deleted successfully")

    })
    public void deleteImage( @PathVariable("id") Long id , @RequestParam String type) {
        categoryService.deleteImage(id, type);
    }
}
