package app.igesa.metiers.pages;
import app.igesa.dto.CookiesDTO;
import app.igesa.dto.Page1DTO;
import app.igesa.entity.Page1;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.metiers.Ientreprise;
import app.igesa.metiers.Ipage1;
import app.igesa.repository.pages.Page1Repository;
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
        Page1 page= new Page1();
        if (page1DTO.getId()!=null){
            page = page1Repository.findById(page1DTO.getId()).orElseThrow(IllegalAccessError::new);
        }
        page.setEntreprise(ientrepriseService.getCurrentEnterprise());
        page.setTitle(page1DTO.getTitle());
        page.setHtmlContent(page1DTO.getHtmlContent());
        Page1 saved = page1Repository.save(Page1DTO.toEntity((page1DTO)));
        return Page1DTO.fromEntity(saved);

    }

    @Override
    public Collection<Page1DTO> view() {
        log.debug("<====================HTTP by current entreprise =====================>");
        return page1Repository.findFirstByEntrepriseId(ientrepriseService.getCurrentEnterprise().getId()).stream()
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
            log.error(" cookies Id is NULL .. !!");
            return null;
        }

        return page1Repository.findById(id).map(Page1DTO::fromEntity).orElseThrow(() ->
                new ResourceNotFoundException(" No cookies   with  Id = :: " + id + " was founded {} ..!"));

    }


}

