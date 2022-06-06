package it.igesa.resources.pages;
import it.igesa.dto.pages.Page3DTO;
import it.igesa.services.Ipage3;
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

/**
 *
 * @author Wassim Hachani
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class Page3Controller {


    /*****************************
     *
     * @Api PRIVATE_API : with token
     *
     **************/

    private final String PRIVATE_API = "/api/private/pages3";

    @Autowired
    Ipage3 ipage3;

    private static final Logger log = LoggerFactory.getLogger(Page3Controller.class);




    @RequestMapping(value=PRIVATE_API + "/post-home-text",method = RequestMethod.POST)
    @ApiOperation(value="ADD Page",notes="SAUVGARDER PAGE", response = Page3DTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page was saved Successfully"),
            @ApiResponse(code=400,message="page not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<Page3DTO> save(@RequestBody Page3DTO p) {
        log.debug(" <==================== HTTP POST {} =============================>",p);
        return new ResponseEntity<> (ipage3.save(p), HttpStatus.CREATED);
    }





    @RequestMapping(value=PRIVATE_API + "/list-page3/{id_enterprise}" ,method = RequestMethod.GET)
    @ApiOperation(value="GET PAGE",notes="PAGES", responseContainer  = "Collection<Page3DTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page was founded Successfully"),
            @ApiResponse(code=400,message="page not found"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<Page3DTO>> view( @PathVariable  Long id_enterprise) {
        log.debug(" <====================== HTTP GET ALL privacy ===================> {}");
        return new ResponseEntity<>( ipage3.view(id_enterprise),HttpStatus.OK);
    }




    @RequestMapping(value=PRIVATE_API  + "/{id}",method = RequestMethod.GET)
    @ApiOperation(value="GET PAGE",notes="GET PAGE", response = Page3DTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page by id was founded Successfully"),
            @ApiResponse(code=400,message="page not founded"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Page3DTO>findById(@PathVariable Long id) {
        log.debug("<====================== HTTP GET PAGE  BY ID {}=========================>",id);
        return new ResponseEntity<>(ipage3.findById(id),HttpStatus.OK);
    }







    @RequestMapping(value=PRIVATE_API ,method =RequestMethod.PUT)
    @ApiOperation(value="UPDATE pages  ",response = Page3DTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Page3DTO>update(@RequestBody Page3DTO c) {
        return new ResponseEntity<>(ipage3.save(c),HttpStatus.CREATED);
    }







    @RequestMapping(value=PRIVATE_API  + "/{id}",method =RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value="DELETE BY ID ",response = Page3DTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Page was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public void delete(@PathVariable Long id) {

        log.debug(" <====================HTTP DELETE BY ID {}===============================>",id);
        ipage3.delete(id);
    }



}
