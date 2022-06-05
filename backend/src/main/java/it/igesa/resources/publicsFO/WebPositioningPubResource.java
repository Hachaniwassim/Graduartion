package it.igesa.resources.publicsFO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.domaine.seo.Seo;
import it.igesa.dto.seo.WebPositioningDto;
import it.igesa.enumerations.PagesTypes;
import it.igesa.services.seo.WebPositioningService;
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
@Api(tags = "WEBPOSITIONNING-PUBLIC-API")
public class WebPositioningPubResource {
    private final String PUBLIC_API = "/api/webPositioning-public";

    @Autowired
    private WebPositioningService webPositioningService;


    @PostMapping(PUBLIC_API  + "/update-web-positioning")
    @ApiOperation(value="update page",notes="update page", response = Seo.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message=" updated  Successfully"),


    })
    private void updatePage(@RequestBody WebPositioningDto dto) {

        webPositioningService.updatePage(dto);
    }

    @GetMapping(PUBLIC_API + "/{page}/{id_entreprise}")
    @ApiOperation(value="get page",notes="get page", response = WebPositioningDto.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page  was founded Successfully"),


    })
    ResponseEntity<Collection<WebPositioningDto>> getPageInfo(@PathVariable PagesTypes page, Long id_entreprise ){
        return  new ResponseEntity<> (webPositioningService.getCurrentEnterprisePageInfo(page, id_entreprise), HttpStatus.OK);
    }


}
