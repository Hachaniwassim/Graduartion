package it.igesa.services.implement;
import it.igesa.dto.legale.PrivacyDTO;
import it.igesa.domaine.Privacy;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.services.Ientreprise;
import it.igesa.services.Iprivacy;
import it.igesa.repository.PrivacyRepository;
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
 * @author Wassim Hachani
 *
 */
@Service
@NoArgsConstructor
@AllArgsConstructor
public class PrivacyImp  implements Iprivacy {
    private static final Logger log = LoggerFactory.getLogger(PrivacyImp.class);
    @Autowired
    Ientreprise ientrepriseService;
    @Autowired
    PrivacyRepository privacyRepository;

    @Override
    public PrivacyDTO updateByEntreprise(PrivacyDTO p) {
        //Privacy privacy = new Privacy();
        //if (p.getId()!=null){
        //   privacy = privacyRepository.findById(p.getId()).orElseThrow(IllegalAccessError::new);
       // }
       // privacy.setEntreprise(ientrepriseService.getCurrentEnterprise());
        //privacy.setTitle(p.getTitle());
       // privacy.setHtmlContent(p.getHtmlContent());
        Privacy saved = privacyRepository.save(PrivacyDTO.toEntity(p));
        return PrivacyDTO.fromEntity(saved);

    }

    /**
     *
     * return collection
     * @param id_entreprise
     * @return
     */

    @Override
    public Collection<PrivacyDTO> FindByEntrepriseId(Long id_entreprise){
        log.debug("HTTP GET ALL {} ..");
        return privacyRepository.findByEntrepriseId(id_entreprise).stream()
                .map(PrivacyDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     *
     * find by id
     * @param id
     * @return
     */
    @Override
    public PrivacyDTO findById(Long id) {
        log.debug("HTTP GET BY ID {} ..", id);
        if (id == null) {
            log.error(" Id is NULL .. !!");
            return null;
        }

        return privacyRepository.findById(id).map(PrivacyDTO::fromEntity).orElseThrow(() ->
                new ResourceNotFoundException(" No cookies  with  Id = :: " + id + " was founded {} ..!"));

    }

    /**
     * delete privacy
     * @param id
     */

    @Override
    public void delete(Long id) {
        log.debug("HTTP DELETE BY ID {} ..", id);
        if (id == null) {
            log.error("ID IS NULL ");
            return;
        }
        privacyRepository.deleteById(id);
}




}


