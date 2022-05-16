package app.igesa.metiers.pages;

import app.igesa.dto.Page1DTO;
import app.igesa.dto.Page3DTO;
import app.igesa.dto.PrivacyDTO;
import app.igesa.entity.Page1;
import app.igesa.entity.Page3;
import app.igesa.entity.Privacy;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.metiers.Ipage1;
import app.igesa.metiers.Ipage3;
import app.igesa.metiers.Iprivacy;
import app.igesa.repository.Page1Repository;
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
public class Page1Imp implements Ipage1 {

    private static final Logger log = LoggerFactory.getLogger(Page1Imp.class);


    @Autowired
    Page1Repository page1Repository;

    @Override
    public Page1DTO save(Page1DTO p) {
        Page1 saved = page1Repository.save(Page1DTO.toEntity(p));
        return Page1DTO.fromEntity(saved);


    }

    @Override
    public Collection<Page1DTO> view() {
        log.debug("HTTP GET ALL {} ..");
        return page1Repository.findAll().stream()
                .map(Page1DTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Page1DTO findById(Long id) {
        log.debug("HTTP GET BY ID {} ..", id);
        if (id == null) {
            log.error(" Id is NULL .. !!");
            return null;
        }

        return page1Repository.findById(id).map(Page1DTO::fromEntity).orElseThrow(() ->
                new ResourceNotFoundException(" No page with  Id = :: " + id + " was founded {} ..!"));

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

}

