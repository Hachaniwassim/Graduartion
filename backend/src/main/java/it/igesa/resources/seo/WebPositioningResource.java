package it.igesa.resources.seo;
import it.igesa.dto.seo.WebPositioningDto;
import it.igesa.domaine.seo.Seo;
import it.igesa.enumerations.PagesTypes;
import it.igesa.services.seo.WebPositioningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 *
 * @author Tarchoun Abir
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(tags = "WebPositioningResource")
public class WebPositioningResource {


    private final String PRIVATE_API = "api/private/web-positioning";

    @Autowired
    private  WebPositioningService webPositioningService;

    public WebPositioningResource(WebPositioningService webPositioningService) {
        this.webPositioningService = webPositioningService;
    }

    @PostMapping(PRIVATE_API + "/update-web-positioning")
    @ApiOperation(value="update page",notes="update page", response = Seo.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message=" updated  Successfully"),


    })
    private void updatePage(@RequestBody WebPositioningDto dto) {

        webPositioningService.updatePage(dto);
    }

    @GetMapping(PRIVATE_API + "/{page}/{id_enterprise}")
    @ApiOperation(value="get page",notes="get page", response = WebPositioningDto.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page  was founded Successfully"),


    })
    ResponseEntity<Collection<WebPositioningDto>> getPageInfo(@PathVariable PagesTypes page, Long id_enterprise) {
        return  new ResponseEntity<> (webPositioningService.getCurrentEnterprisePageInfo(page,id_enterprise), HttpStatus.OK);
    }




}
