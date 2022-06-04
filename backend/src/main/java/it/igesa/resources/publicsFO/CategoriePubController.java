package it.igesa.controller.publicsFO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "CATEGORY-PUBLIC-API")
public class CategoriePubController {}

 /*

    private final String PUBLIC_API = "/api/category-public";

        @Autowired
        private Icategory categoryService ;


        @RequestMapping(value=PUBLIC_API + "/list-category/{enterprise_id}",method =RequestMethod.GET)
        @ApiOperation(value="GET A LIST OF CATEGORY ", responseContainer  = "Collection<CategoryDTO")
        @ApiResponses(value= {
                @ApiResponse(code=200,message="Category was found successfully")

        })
        public ResponseEntity<Collection<CategoryDTO>> view(@RequestHeader(app.igesa.constans.Consts.ENTERPRISE_ID,, required = false) Long enterprise_id) {

            return new ResponseEntity<>( categoryService.getAllByEntreprise(enterprise_id),HttpStatus.OK);
        }


        @RequestMapping(value=PUBLIC_API + "/{id}",method =RequestMethod.GET)
        @ApiOperation(value=" GET CATEGORY BY ID ",notes="GET AND SEARCH FOR CATEGORY BY ID ", response = CategoryDTO.class)
        @ApiResponses(value= {
                @ApiResponse(code=200,message="Category was found successfully with the provided id"),
                @ApiResponse(code=404,message="No Category is found with the provided id ")

        })     return new ResponseEntity<>( categoryService.findById(id),HttpStatus.OK);
        }




}*/
