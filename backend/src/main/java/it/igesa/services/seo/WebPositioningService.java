package it.igesa.services.seo;
import it.igesa.dto.seo.WebPositioningDto;
import it.igesa.enumerations.PagesTypes;

import java.util.Collection;

/** @author Tarchoun Abir
 *
 */
public interface WebPositioningService {
    public WebPositioningDto updatePage(WebPositioningDto request);
    Collection<WebPositioningDto> getCurrentEnterprisePageInfo(PagesTypes page, Long id_enterprise);
}
