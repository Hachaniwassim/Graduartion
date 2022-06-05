package it.igesa.resources.publicsFO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.dto.ProductDTO;
import it.igesa.services.Iproduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

/**
 *
 * @author  Tarchoun Abir
 *
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "PRODUCT-PUBLIC-API")
public class ProductPubController {

    private final String PUBLIC_API = "/api/product-public";

    @Autowired
    private Iproduct iproductService;


    @RequestMapping(value= PUBLIC_API + "/list-products/{entreprise_id}",method =RequestMethod.GET)

    @ApiOperation(value="GET A LIST OF PRODUCT", responseContainer  = "Collection<ProductDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Product was found successfully"),
            @ApiResponse( code=404, message="Product Not found ")

    })
    public ResponseEntity<Collection<ProductDTO>> view(@PathVariable Long entreprise_id) {
        return new ResponseEntity<>( iproductService.view(entreprise_id),HttpStatus.OK);
    }


    @RequestMapping(value= PUBLIC_API+ "/{id}",method =RequestMethod.GET)
    @ApiOperation(value=" GET PRODUCT BY ID ",notes="GET AND SEARCH FOR PRODUCT BY ID ", response = ProductDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Product was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Product  is found with the provided id "),


    })
    public ResponseEntity<ProductDTO>findById(@PathVariable Long id) {
        return new ResponseEntity<>(iproductService.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value= PUBLIC_API + "/get-tags-by-product/{product_id}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="Get tag BY ID ",responseContainer  = "Collection<ProductDTO>")
    @ApiResponses(value= {

            @ApiResponse(code=200,message="Product was Deleted successfully"),

    })
    public ResponseEntity<Collection<String>> getTagsByProduct(@PathVariable Long product_id) {

        return new ResponseEntity<>(iproductService.getTagsByProduct(product_id), HttpStatus.OK);
    }
}
