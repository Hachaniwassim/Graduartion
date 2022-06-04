package it.igesa.services.seo;
import it.igesa.dto.seo.WebPositioningDto;
import it.igesa.domaine.seo.Seo;
import it.igesa.enumerations.PagesTypes;
import it.igesa.repository.seo.WebPositioningRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Tarchoun Abir
 *
 */

@Service
public class WebPositioningServiceImpl implements WebPositioningService {
    private final WebPositioningRepository webPositioningRepository;

    public WebPositioningServiceImpl(
            WebPositioningRepository webPositioningRepository) {
            this.webPositioningRepository = webPositioningRepository;
    }


    @Override
    public WebPositioningDto updatePage(WebPositioningDto request) {
        Seo saved = webPositioningRepository.save(WebPositioningDto.toEntity(request));
        return WebPositioningDto.fromEntity(saved);

    }


    @Override
    public Collection<WebPositioningDto> getCurrentEnterprisePageInfo(PagesTypes page, Long id_enterprise) {
                return webPositioningRepository.findByEntrepriseId(id_enterprise).stream()
                .map(WebPositioningDto::fromEntity)
                .collect(Collectors.toList());
    }

}
