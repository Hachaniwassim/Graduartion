package it.igesa.resources.publicsFO;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.dto.pages.Page3DTO;
import it.igesa.services.Ipage3;
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
@Api(tags = "PAGE3-PUBLIC-API")
public class Page3PubController {

    private final String PUBLIC_API = "/api/page-home-public";

    @Autowired
    Ipage3 ipage3;

    private static final Logger log = LoggerFactory.getLogger(Page2PubController.class);



    @RequestMapping(value=PUBLIC_API + "/home-page/{id_enterprise}",method = RequestMethod.GET)
    @ApiOperation(value="GET PAGE",notes="PAGES", responseContainer  = "Collection<Page3DTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page was founded Successfully"),
            @ApiResponse(code=400,message="page not found"),

    })
    public ResponseEntity<Collection<Page3DTO>> view(@PathVariable Long id_enterprise) {
        log.debug("<================= HTTP GET ALL   {}=======================>");
        return new ResponseEntity<>( ipage3.view(id_enterprise), HttpStatus.OK);
    }



}
