package app.igesa.metiers.seo;
import app.igesa.dto.seo.WebPositioningDto;
import app.igesa.entity.seo.WebPositioningClientResponse;
import app.igesa.enumerations.PagesTypes;

import java.util.Locale;

/** @author Tarchoun Abir
 *
 */
public interface WebPositioningService {
    void updatePage(WebPositioningDto request);
    WebPositioningDto getCurrentEnterprisePageInfo(PagesTypes page);
    WebPositioningClientResponse getClientPageInfo(PagesTypes page, Locale locale, Long enterpriseId);
}
