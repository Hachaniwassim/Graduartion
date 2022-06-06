package it.igesa.resources.publicsFO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.dto.pages.Page1DTO;
import it.igesa.services.Ipage1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

/**
 *
 * @author  Tarchoun Abir
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "PAGE1-PUBLIC-API")

public class Page1PubController {

    private final String PUBLIC_API = "/api/about-public";
    @Autowired
    Ipage1 ipage1;

    private static final Logger log = LoggerFactory.getLogger(Page1PubController.class);



    @RequestMapping(value=PUBLIC_API + "/about-page/{id_enterprise}",method = RequestMethod.GET)
    @ApiOperation(value="GET PAGE",notes="PAGES", responseContainer  = "Collection<Page1DTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page was founded Successfully"),
            @ApiResponse(code=400,message="page not found"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<Page1DTO>> view(@PathVariable Long id_enterprise) {
        log.debug("<================= HTTP GET ALL privacy  {}=======================>");
        return new ResponseEntity<>( ipage1.view(id_enterprise),HttpStatus.OK);
    }



}
