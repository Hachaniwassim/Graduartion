package it.igesa.resources.publicsFO;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.dto.pages.PageDTO;
import it.igesa.services.Ipages;
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
@Api(tags = "PAGE-PUBLIC-API")
public class PagePubController {
    private final String PUBLIC_API = "/api/pages-public";
    @Autowired
    private Ipages ipagesService;

    @RequestMapping(value=PUBLIC_API + "/pages-revendeur/{id_enterprise}" ,method = RequestMethod.GET)
    @ApiOperation(value="GET A LIST OF PAGE ", responseContainer  = "Collection<PageDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Page was found successfully"),
            @ApiResponse( code=404, message="Page Not found ")

    })
    public ResponseEntity<Collection<PageDTO>> view(@PathVariable Long id_enterprise) {

        return new ResponseEntity<>( ipagesService.view(id_enterprise), HttpStatus.OK);
    }


}
