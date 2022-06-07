package it.igesa.services.pages;
import it.igesa.dto.pages.Page2DTO;
import it.igesa.domaine.pages.Page2;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.services.Ientreprise;
import it.igesa.services.Ipage2;
import it.igesa.repository.pages.Page2Repository;
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
public class Page2Imp implements Ipage2 {

        private static final Logger log = LoggerFactory.getLogger(Page2Imp.class);

        @Autowired
        Page2Repository page2Repository;
        @Autowired
        Ientreprise ientrepriseService;

        @Override
        public Page2DTO save(Page2DTO p) {
           /* Page2 pages= new Page2();
            if (p.getId()!=null){
                pages = page2Repository.findById(p.getId()).orElseThrow(IllegalAccessError::new);
            }
            pages.setEntreprise(ientrepriseService.getCurrentEnterprise());
            pages.setTitle(p.getTitle());
            pages.setHtmlContent(p.getHtmlContent());*/
            Page2 saved = page2Repository.save(Page2DTO.toEntity((p)));
            return Page2DTO.fromEntity(saved);

        }

        @Override
        public Collection<Page2DTO> view(Long id_entreprise) {
            log.debug("<====================HTTP by current entreprise =====================>");
            return page2Repository.findByEntrepriseId(id_entreprise).stream()
                    .map(Page2DTO::fromEntity)
                    .collect(Collectors.toList());
        }
        @Override
        public Page2DTO findById(Long id) {
            log.debug("<=======================HTTP GET BY ID {}=====================>", id);
            if (id == null) {
                log.error(" Id is NULL .. !!");
                return null;
            }

            return page2Repository.findById(id).map(Page2DTO::fromEntity).orElseThrow(() ->
                    new ResourceNotFoundException(" No page with  Id = :: " + id + " was founded {} ..!"));

        }

        @Override
        public void delete(Long id) {
            log.debug("<=======================HTTP DELETE BY ID {}==================>", id);
            if (id == null) {
                log.error("ID IS NULL ");
                return;
            }
            page2Repository.deleteById(id);
        }

    }

