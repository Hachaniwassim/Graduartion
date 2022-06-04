package it.igesa.services.implement;
import it.igesa.dto.LanguageDTO;
import it.igesa.domaine.Language;
import it.igesa.enumerations.ErrorCode;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.services.Ilanguage;
import it.igesa.repository.IlanguageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Wassim Hachani
 *
 */
@Service
@NoArgsConstructor
@AllArgsConstructor
public class LanguageImp  implements Ilanguage {

    @Autowired
    private IlanguageRepository ilanguageRepository;
    private static final Logger log = LoggerFactory.getLogger(LanguageImp.class);


    @Override
    public LanguageDTO save(LanguageDTO L) {
           Language saved =ilanguageRepository.save(LanguageDTO.toEntity(L));
            return LanguageDTO.fromEntity(saved);

    }

    @Override
    public Collection<LanguageDTO> view(Long id_entreprise) {
        log.debug("HTTP GET ALL {} ..");
        return ilanguageRepository.findByEntrepriseId(id_entreprise).stream()
                .map(LanguageDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public LanguageDTO findById(Long id) {
        log.debug("HTTP GET BY ID {} ..",id);
        if ( id == null) {
            log.error(" Language  Id is NULL .. !!");
            return null ;
        }

        return  ilanguageRepository.findById(id).map(LanguageDTO::fromEntity).orElseThrow(()->
                new ResourceNotFoundException(" No Language   with  Id = :: " +id+ " was founded {} ..!",
                        ErrorCode.LANGUAGE_NOT_FOUND));

    }

    @Override
    public void delete(Long id) {
        log.debug("HTTP DELETE BY ID {} ..",id);
        if ( id == null) {
            log.error(" LANGUAGE ID IS NULL ");
            return;
        }
       ilanguageRepository.deleteById(id);
    }


}
