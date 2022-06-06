package it.igesa.resources.pages;

import it.igesa.dto.pages.Page2DTO;
import it.igesa.services.Ipage2;
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
 * @author wassim Hachanni
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class Page2Controller {

    /*****************************
     *
     * @Api PRIVATE_API : with token
     *
     **************/

    private final String PRIVATE_API = "/api/private/pages2";
    @Autowired
    Ipage2 ipage2;

    private static final Logger log = LoggerFactory.getLogger(Page2Controller.class);
    @RequestMapping(value=PRIVATE_API + "/post-assitance" ,method = RequestMethod.POST)
    @ApiOperation(value="ADD Page",notes="SAUVGARDER PAGE", response = Page2DTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="pagewas saved Successfully"),
            @ApiResponse(code=400,message="page not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<Page2DTO> save(@RequestBody Page2DTO p) {
        log.debug(" <======================= HTTP POST {}====================>",p);
        return new ResponseEntity<> (ipage2.save(p), HttpStatus.CREATED);
    }




    @RequestMapping(value=PRIVATE_API + "/list-page2/{id_enterprise}",method = RequestMethod.GET)
    @ApiOperation(value="GET PAGE",notes="PAGES", responseContainer  = "Collection<Page2DTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page was founded Successfully"),
            @ApiResponse(code=400,message="page not found"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<Page2DTO>> view( @PathVariable Long id_enterprise) {
        log.debug("<================= HTTP GET ALL privacy  {}=======================>");
        return new ResponseEntity<>( ipage2.view(id_enterprise),HttpStatus.OK);
    }



    @RequestMapping(value=PRIVATE_API + "/{id}",method = RequestMethod.GET)
    @ApiOperation(value="GET PAGE",notes="GET PAGE", response = Page2DTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page by id was founded Successfully"),
            @ApiResponse(code=400,message="page not founded"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Page2DTO>findById(@PathVariable Long id) {
        log.debug("<=======================HTTP GET PAGE  BY ID {}===================>",id);
        return new ResponseEntity<>(ipage2.findById(id),HttpStatus.OK);
    }




    @RequestMapping(value=PRIVATE_API ,method =RequestMethod.PUT)
    @ApiOperation(value="UPDATE pages  ",response = Page2DTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Page2DTO>update(@RequestBody Page2DTO c) {
        return new ResponseEntity<>(ipage2.save(c),HttpStatus.CREATED);
    }




    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value="DELETE BY ID ",response = Page2DTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Page was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public void delete(@PathVariable Long id) {
        log.debug("<=================HTTP DELETE BY ID {}==================>",id);
        ipage2.delete(id);
    }



}