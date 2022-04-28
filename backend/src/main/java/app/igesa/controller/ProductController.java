package app.igesa.controller;

import app.igesa.dto.EntrepriseDTO;
import app.igesa.dto.ProductDTO;
import app.igesa.metiers.Ientreprise;
import app.igesa.metiers.Iproduct;
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
@Api(tags = "PRODUCT")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private Iproduct iproductService;


    @RequestMapping(value="/product",method = RequestMethod.POST)
    @ApiOperation(value="ADD PRODUCT",notes="SAUVGARDER PRODUCT", response = ProductDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Product was saved Successfully"),
            @ApiResponse(code=400,message="Productnot valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<ProductDTO> save( @RequestBody ProductDTO p) {
        log.debug(" HTTP POST {}",p);
        return new ResponseEntity<> (iproductService.save(p), HttpStatus.CREATED);
    }


    @RequestMapping(value="/product",method =RequestMethod.GET)
    @ApiOperation(value="GET A LIST OF PRODUCT", responseContainer  = "Collection<ProductDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Product was found successfully"),
            @ApiResponse( code=404, message="Product Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<ProductDTO>> view() {
        log.debug(" HTTP GET ALL PRODUCT {}");
        return new ResponseEntity<>( iproductService.view(),HttpStatus.OK);
    }


    @RequestMapping(value="/product/{id}",method =RequestMethod.GET)
    @ApiOperation(value=" GET PRODUCT BY ID ",notes="GET AND SEARCH FOR PRODUCT BY ID ", response = ProductDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Product was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Product  is found with the provided id "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<ProductDTO>findById(@PathVariable Long id) {
        log.debug(" HTTP GET PRODUCT BY ID {}",id);
        return new ResponseEntity<>(iproductService.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value="/product/{id}",method =RequestMethod.PUT)
    @ApiOperation(value="UPDATE PRODUCT BY ID ",response = ProductDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Product was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO p) {
        return new ResponseEntity<>(iproductService.save(p),HttpStatus.CREATED);
    }

    @RequestMapping(value="/product/{id}",method =RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value="DELETE PRODUCT BY ID ",response = ProductDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Product was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public void delete(@PathVariable Long id) {

        log.debug(" HTTP DELETE PRODUCT BY ID {}",id);

       iproductService.delete(id);
    }


}
