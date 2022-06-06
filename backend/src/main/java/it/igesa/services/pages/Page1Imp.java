package it.igesa.services.pages;
import it.igesa.dto.pages.Page1DTO;
import it.igesa.domaine.pages.Page1;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.services.Ientreprise;
import it.igesa.services.Ipage1;
import it.igesa.repository.pages.Page1Repository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 * Wassim Hachaani
 *
 */
@Service
@NoArgsConstructor
@AllArgsConstructor
public class Page1Imp implements Ipage1 {

    private static final Logger log = LoggerFactory.getLogger(Page1Imp.class);


    @Autowired
    Page1Repository page1Repository;
    @Autowired
    Ientreprise ientrepriseService;

    @Override
    public Page1DTO save(Page1DTO page1DTO) {
        Page1 saved = page1Repository.save(Page1DTO.toEntity((page1DTO)));
        return Page1DTO.fromEntity(saved);

    }

    @Override
    public Collection<Page1DTO> view(Long id_enterprise) {
        log.debug("<==================== HTTP by current entreprise =====================>");
        return page1Repository.findByEntrepriseId(id_enterprise).stream()
                .map(Page1DTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.debug("HTTP DELETE BY ID {} ..", id);
        if (id == null) {
            log.error("ID IS NULL ");
            return;
        }
        page1Repository.deleteById(id);
    }
    @Override
    public Page1DTO findById(Long id) {
        log.debug("HTTP GET BY ID {} ..", id);
        if (id == null) {
            log.error("  Id is NULL .. !!");
            return null;
        }

        return page1Repository.findFirstByEntrepriseId(ientrepriseService.getCurrentEnterprise().getId()).map(Page1DTO::fromEntity).orElseThrow(() ->
                new ResourceNotFoundException(" No page  with  Id = :: " + id + " was founded {} ..!"));

    }


}

