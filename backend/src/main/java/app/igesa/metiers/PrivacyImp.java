package app.igesa.metiers;

import app.igesa.dto.CompanyBusinessDTO;
import app.igesa.dto.PrivacyDTO;
import app.igesa.entity.CompanyBusiness;
import app.igesa.entity.Privacy;

import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.repository.PrivacyRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PrivacyImp  implements  Iprivacy{

    private static final Logger log = LoggerFactory.getLogger(PrivacyImp.class);


    @Autowired
    PrivacyRepository privacyRepository;

    @Override
    public PrivacyDTO save(PrivacyDTO p) {
       Privacy saved = privacyRepository.save(PrivacyDTO.toEntity(p));
        return PrivacyDTO.fromEntity(saved);


    }

    @Override
    public Collection<PrivacyDTO> view() {
        log.debug("HTTP GET ALL {} ..");
        return privacyRepository.findAll().stream()
                .map(PrivacyDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PrivacyDTO findById(Long id) {
        log.debug("HTTP GET BY ID {} ..", id);
        if (id == null) {
            log.error(" Privacy busniss Id is NULL .. !!");
            return null;
        }

        return privacyRepository.findById(id).map(PrivacyDTO::fromEntity).orElseThrow(() ->
                new ResourceNotFoundException(" No privacy   with  Id = :: " + id + " was founded {} ..!"));

    }

    @Override
    public void delete(Long id) {
        log.debug("HTTP DELETE BY ID {} ..", id);
        if (id == null) {
            log.error(" ENTREPRISE ID IS NULL ");
            return;
        }
        privacyRepository.deleteById(id);
}

}


