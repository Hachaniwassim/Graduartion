package app.igesa.controller;

import app.igesa.dto.CategoryDTO;
import app.igesa.dto.EntrepriseDTO;
import app.igesa.metiers.Icategory;
import app.igesa.metiers.Ientreprise;
import app.igesa.repository.IcategoryRepository;
import io.swagger.annotations.Api;
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
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(tags = "CATEGORY")
public class CategoryController {
    private static final Logger log = LoggerFactory.getLogger(EntrepriseController.class);

    @Autowired
    private Icategory categoryService ;




    @RequestMapping(value="/category",method = RequestMethod.POST)
    @ApiOperation(value="ADD CATEGORY",notes="SAUVGARDER CATEGORY", response = CategoryDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Category  was saved Successfully"),
            @ApiResponse(code=400,message="Category not valid")

    })
    ResponseEntity<CategoryDTO> save( @RequestBody CategoryDTO c) {
        log.debug(" HTTP POST {}",c);
        return new ResponseEntity<> (categoryService.save(c), HttpStatus.CREATED);
    }


    @RequestMapping(value="/category",method =RequestMethod.GET)
    @ApiOperation(value="GET A LIST OF CATEGORY ", responseContainer  = "Collection<CategoryDTO")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Category was found successfully")

    })
    public ResponseEntity<Collection<CategoryDTO>> view() {
        log.debug(" HTTP GET ALL Category {}");
        return new ResponseEntity<>( categoryService.view(),HttpStatus.OK);
    }


    @RequestMapping(value="/category/{id}",method =RequestMethod.GET)
    @ApiOperation(value=" GET CATEGORY BY ID ",notes="GET AND SEARCH FOR CATEGORY BY ID ", response = CategoryDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Category was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Category is found with the provided id ")

    })
    public ResponseEntity <CategoryDTO> findById(@PathVariable Long id) {
        log.debug(" HTTP GET Category BY ID {}",id);
        return new ResponseEntity<>( categoryService.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value="/category",method =RequestMethod.PUT)
    @ApiOperation(value="UPDATE CATEGORY ",response = CategoryDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Category was updated successfully")

    })
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO c ) {
        return new ResponseEntity<>(categoryService.save(c),HttpStatus.CREATED);
    }

    @RequestMapping(value="/category/{id}",method =RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value="DELETE CATEGORY BY ID ",response = CategoryDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Category was Deleted successfully")

    })
    public void delete(@PathVariable Long id) {

        log.debug(" HTTP DELETE CATEGORY BY ID {}",id);

        categoryService.delete(id);
    }

}
