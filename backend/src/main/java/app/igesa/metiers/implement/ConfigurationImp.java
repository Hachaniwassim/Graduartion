package app.igesa.metiers.implement;

import app.igesa.dto.ConfigGeneralDTO;
import app.igesa.entity.ConfigGenerale;
import app.igesa.entity.Entreprise;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.metiers.Iconfiguration;
import app.igesa.repository.IconfigRepository;
import app.igesa.repository.IentrepriseRepository;
import app.igesa.validators.ConfigValidator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Wassim Hachaani
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ConfigurationImp implements Iconfiguration {
    @Autowired
    IentrepriseRepository ientrepriseRepository;
    @Autowired
    IconfigRepository iconfigRepository;
    private static final Logger log = LoggerFactory.getLogger(ConfigurationImp.class);


    @Override
    public ConfigGeneralDTO save(ConfigGeneralDTO c) {
        ConfigGenerale saved =iconfigRepository.save(ConfigGeneralDTO.toEntity(c));
        return ConfigGeneralDTO.fromEntity(saved);
    }

    @Override
    public Collection<ConfigGeneralDTO> view(Long enterprise_id) {
        return iconfigRepository.findByEntrepriseId(enterprise_id).stream()
                .map(ConfigGeneralDTO::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public ConfigGeneralDTO findById(Long id) {
        if ( id == null) {
            log.error(" Config Id is NULL .. !!");
            return null ;
        }

        return iconfigRepository.findById(id).map(ConfigGeneralDTO::fromEntity).orElseThrow(()->
                new ResourceNotFoundException(" No Config with  Id = :: " +id+ " was founded {} ..!",
                        ErrorCode.CONFIGURATION_NOT_FOUND));

    }

    @Override
    public void delete(Long id) {
        if ( id == null) {
            log.error(" Config ID IS NULL ");
            return;
        }
        iconfigRepository.deleteById(id);
    }


}
