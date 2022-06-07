package it.igesa.resources.pages;
import it.igesa.dto.pages.Page1DTO;
import it.igesa.services.Ipage1;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class Page1Controller {

    /***************
     *
     *@Api PRIVATE_API : with token
     *
     *********/

    private final String PRIVATE_API = "/api/private/pages1";

    @Autowired
    Ipage1 ipage1Service;

    private static final Logger log = LoggerFactory.getLogger(Page1Controller.class);




    @RequestMapping(value=PRIVATE_API + "/post-about",method = RequestMethod.POST)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="ADD Page",notes="SAUVGARDER PAGE", response = Page1DTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="pagewas saved Successfully"),
            @ApiResponse(code=400,message="page not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<Page1DTO> save(@RequestBody Page1DTO p) {
        log.debug(" <=================== HTTP POST {}========================>",p);
        return new ResponseEntity<> (ipage1Service.save(p), HttpStatus.CREATED);
    }





    @RequestMapping(value=PRIVATE_API + "/list-page1/{id_enterprise}",method = RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET PAGE",notes="PAGES", responseContainer  = "Collection<Page1DTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page was founded Successfully"),
            @ApiResponse(code=400,message="page not found"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<Page1DTO>> view( @PathVariable Long id_enterprise) {
        log.debug(" <==================== HTTP GET ALL privacy  {}====================>");
        return new ResponseEntity<>( ipage1Service.view(id_enterprise),HttpStatus.OK);
    }





    @RequestMapping(value=PRIVATE_API + "/{id}",method = RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET PAGE",notes="GET PAGE", response = Page1DTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page by id was founded Successfully"),
            @ApiResponse(code=400,message="page not founded"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Page1DTO>findById(@PathVariable Long id) {
        log.debug(" <===================HTTP GET PAGE  BY ID {}=======================>",id);
        return new ResponseEntity<>(ipage1Service.findById(id),HttpStatus.OK);
    }





    @RequestMapping(value=PRIVATE_API,method =RequestMethod.PUT)
    @ApiOperation(value="UPDATE pages  ",response = Page1DTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Page1DTO>update(@RequestBody Page1DTO c) {

        log.debug(" <=================== Update {}=======================>");
        return new ResponseEntity<>(ipage1Service.save(c),HttpStatus.CREATED);
    }






    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.DELETE)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value="DELETE BY ID ",response = Page1DTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Page was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public void delete(@PathVariable Long id) {
        log.debug("<========================== HTTP DELETE BY ID {}========================>",id);
        ipage1Service.delete(id);
    }

}
