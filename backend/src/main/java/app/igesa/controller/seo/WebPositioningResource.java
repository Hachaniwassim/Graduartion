package app.igesa.controller.seo;
import app.igesa.dto.CategoryDTO;
import app.igesa.dto.seo.WebPositioningDto;
import app.igesa.entity.seo.Meta;
import app.igesa.entity.seo.WebPositioningClientResponse;
import app.igesa.enumerations.PagesTypes;
import app.igesa.metiers.seo.WebPositioningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;

/**
 *
 * @author Tarchoun Abir
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(tags = "WebPositioningResource")
public class WebPositioningResource {
    private final String PUBLIC_API = "api/web-positioning";
    private final String PRIVATE_API = "api/private/web-positioning";

    private final WebPositioningService webPositioningService;

    public WebPositioningResource(WebPositioningService webPositioningService) {
        this.webPositioningService = webPositioningService;
    }

    @PostMapping(PRIVATE_API)
    @ApiOperation(value="update page",notes="update page", response = Meta.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message=" updated  Successfully"),


    })
    private void updatePage(@RequestBody WebPositioningDto dto) {

        webPositioningService.updatePage(dto);
    }

    @GetMapping(PRIVATE_API + "/{page}")
    @ApiOperation(value="get page",notes="get page", response = WebPositioningDto.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="page  was founded Successfully"),


    })
    private WebPositioningDto getPageInfo(@PathVariable PagesTypes page) {
        return webPositioningService.getCurrentEnterprisePageInfo(page);
    }




}
