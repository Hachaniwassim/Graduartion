package app.igesa.metiers;

import app.igesa.dto.LanguageDTO;
import app.igesa.entity.Language;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.repository.IlanguageRepository;
import app.igesa.validators.LanguageValidators;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class LanguageImp  implements Ilanguage{

    @Autowired
    private IlanguageRepository ilanguageRepository;
    private static final Logger log = LoggerFactory.getLogger(LanguageImp.class);


    @Override
    public LanguageDTO save(LanguageDTO L) {
        List<String> errors = LanguageValidators.validate(L);
            log.debug("HTTP POST {} ..",L);
            if(!errors.isEmpty()) {
            log.error("Language not valid !" ,L);
            throw new InvalideEntityException("Language NOT VALID!", ErrorCode.LANGUAGE_NOT_VALID,errors);
            }
           Language saved =ilanguageRepository.save(LanguageDTO.toEntity(L));
            return LanguageDTO.fromEntity(saved);

    }

    @Override
    public Collection<LanguageDTO> view() {
        log.debug("HTTP GET ALL {} ..");
        return ilanguageRepository.findAll().stream()
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



    @Override
    public LanguageDTO update(LanguageDTO L, Long id) {
        return null;
    }
}
