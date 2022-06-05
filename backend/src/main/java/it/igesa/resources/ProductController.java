package it.igesa.resources;
import it.igesa.dto.ProductDTO;
import it.igesa.services.Iproduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tarchoun Abir
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "PRODUCT")
public class ProductController {



    /**************************
     *
     *@PRIVATE_API : with token
     *
     ******/
    private final String PRIVATE_API = "api/private/product";

    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    @Autowired
    private Iproduct iproductService;


    @RequestMapping(value= PRIVATE_API + "/post-product",method = RequestMethod.POST)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
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


    @RequestMapping(value= PRIVATE_API + "/list-products/{entreprise_id}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET A LIST OF PRODUCT", responseContainer  = "Collection<ProductDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Product was found successfully"),
            @ApiResponse( code=404, message="Product Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<ProductDTO>> view( @PathVariable Long entreprise_id) {
        log.debug(" HTTP GET ALL PRODUCT {}");
        return new ResponseEntity<>( iproductService.view(entreprise_id),HttpStatus.OK);
    }


    @RequestMapping(value= PRIVATE_API + "/{id}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
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


    @RequestMapping(value= PRIVATE_API + "/update-product",method =RequestMethod.PUT)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="UPDATE PRODUCT BY ID ",response = ProductDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Product was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO p) {
        return new ResponseEntity<>(iproductService.save(p),HttpStatus.CREATED);
    }

    @RequestMapping(value= PRIVATE_API + "/{id}",method =RequestMethod.DELETE)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value="DELETE PRODUCT BY ID ",response = ProductDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Product was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public ResponseEntity delete(@PathVariable Long id) {
        log.debug(" HTTP DELETE PRODUCT BY ID {}",id);
       iproductService.delete(id);
        return new ResponseEntity<>("{code :200 ,msg : deleted successfully}",HttpStatus.OK);
    }

    @RequestMapping(value= PRIVATE_API + "/assign-tags/{product_id}/{tag_id}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value="DELETE PRODUCT BY ID ",response = ProductDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Product was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public ResponseEntity assignTags(@PathVariable Long product_id,@PathVariable Long tag_id) {

        iproductService.assignTags(product_id,tag_id);
        return new ResponseEntity<>("{assignTags Succesfully}",HttpStatus.OK);
    }


    @RequestMapping(value= PRIVATE_API + "/get-tags-by-product/{product_id}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value="Get Tag by Product",responseContainer  = "Collection<ProductDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Product was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public ResponseEntity<Collection<String>>getTagsByProduct(@PathVariable Long product_id) {

        return new ResponseEntity<>(iproductService.getTagsByProduct(product_id),HttpStatus.OK);
    }

}
