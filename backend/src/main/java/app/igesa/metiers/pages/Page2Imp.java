package app.igesa.metiers.pages;

import app.igesa.dto.Page2DTO;
import app.igesa.dto.Page3DTO;
import app.igesa.dto.PrivacyDTO;
import app.igesa.entity.Page2;
import app.igesa.entity.Page3;
import app.igesa.entity.Privacy;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.metiers.Ipage2;
import app.igesa.metiers.Ipage3;
import app.igesa.metiers.Iprivacy;
import app.igesa.repository.Page2Repository;
import app.igesa.repository.Page3Repository;
import app.igesa.repository.PrivacyRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

    /**
     * Wassim Hachaani
     */
    @Service
    @NoArgsConstructor
    @AllArgsConstructor
public class Page2Imp implements Ipage2 {

        private static final Logger log = LoggerFactory.getLogger(app.igesa.metiers.pages.Page2Imp.class);


        @Autowired
        Page2Repository page2Repository;

        @Override
        public Page2DTO save(Page2DTO p) {
            Page2 saved = page2Repository.save(Page2DTO.toEntity(p));
            return Page2DTO.fromEntity(saved);


        }

        @Override
        public Collection<Page2DTO> view() {
            log.debug("HTTP GET ALL {} ..");
            return page2Repository.findAll().stream()
                    .map(Page2DTO::fromEntity)
                    .collect(Collectors.toList());
        }

        @Override
        public Page2DTO findById(Long id) {
            log.debug("HTTP GET BY ID {} ..", id);
            if (id == null) {
                log.error(" Id is NULL .. !!");
                return null;
            }

            return page2Repository.findById(id).map(Page2DTO::fromEntity).orElseThrow(() ->
                    new ResourceNotFoundException(" No page with  Id = :: " + id + " was founded {} ..!"));

        }

        @Override
        public void delete(Long id) {
            log.debug("HTTP DELETE BY ID {} ..", id);
            if (id == null) {
                log.error("ID IS NULL ");
                return;
            }
            page2Repository.deleteById(id);
        }

    }

