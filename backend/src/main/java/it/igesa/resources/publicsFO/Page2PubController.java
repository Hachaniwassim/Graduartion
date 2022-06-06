package it.igesa.resources.publicsFO;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.dto.pages.Page2DTO;
import it.igesa.resources.pages.Page2Controller;
import it.igesa.services.Ipage2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author  Tarchoun Abir
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "PAGE2-PUBLIC-API")
public class Page2PubController {
    private final String PUBLIC_API = "/api/page-assistance-public";

    @Autowired
    Ipage2 ipage2;

    private static final Logger log = LoggerFactory.getLogger(Page2Controller.class);



    @RequestMapping(value=PUBLIC_API + "/list-assistance/{id_enterprise}",method = RequestMethod.GET)
    @ApiOperation(value="GET PAGE",notes="PAGES", responseContainer  = "Collection<Page2DTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page was founded Successfully"),
            @ApiResponse(code=400,message="page not found")

    })
    public ResponseEntity<Collection<Page2DTO>> view(@PathVariable Long id_enterprise) {
        log.debug("<================= HTTP GET ALL privacy  {}=======================>");
        return new ResponseEntity<>( ipage2.view(id_enterprise), HttpStatus.OK);
    }



}
