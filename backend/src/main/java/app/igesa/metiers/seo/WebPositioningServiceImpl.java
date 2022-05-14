package app.igesa.metiers.seo;
import app.igesa.dto.seo.WebPositioningDto;
import app.igesa.entity.seo.Meta;
import app.igesa.entity.seo.WebPositioningClientResponse;
import app.igesa.entity.seo.WebPositioningPk;
import app.igesa.enumerations.PagesTypes;
import app.igesa.metiers.EntrepriseImpl;
import app.igesa.repository.seo.WebPositioningRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author Tarchoun Abir
 *
 */

@Service
public class WebPositioningServiceImpl implements WebPositioningService {
    private final EntrepriseImpl enterpriseService;
    private final WebPositioningRepository webPositioningRepository;

    public WebPositioningServiceImpl(
            EntrepriseImpl enterpriseService,
            WebPositioningRepository webPositioningRepository) {
        this.enterpriseService = enterpriseService;
        this.webPositioningRepository = webPositioningRepository;
    }


    @Override
    public void updatePage(WebPositioningDto request) {
        WebPositioningPk webPositioningPk = buildWebPositioningPk(request.getPage(), enterpriseService.getCurrentEnterprise().getId());
        Meta webPositioning = webPositioningRepository
                .findById(webPositioningPk)
                .orElse(null);
        if (webPositioning == null) {
            webPositioning = new Meta();
            webPositioning.setId(webPositioningPk);
            webPositioning.setEnterprise(enterpriseService.getCurrentEnterprise());
        }
        webPositioning.setRobots(request.getRobots());
        webPositioningRepository.save(webPositioning);
    }



    @Override
    public WebPositioningDto getCurrentEnterprisePageInfo(PagesTypes page) {
        WebPositioningPk webPositioningPk = buildWebPositioningPk(page, enterpriseService.getCurrentEnterprise().getId());
        Meta webPositioning = webPositioningRepository
                .findById(webPositioningPk)
                .orElse(null);
        if (webPositioning == null) {
            return null;
        }
        return WebPositioningDto.builder()
                .page(page)
                .robots(webPositioning.getRobots())
                .build();
    }

    @Override
    public WebPositioningClientResponse getClientPageInfo(PagesTypes page, Locale locale, Long enterpriseId) {
    return null;

    }

    private WebPositioningPk buildWebPositioningPk(PagesTypes page, Long enterpriseId) {
        return WebPositioningPk.builder()
                .page(page)
                .enterprise(enterpriseId)
                .build();
    }
}
