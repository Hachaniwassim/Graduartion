package it.igesa.services.implement;
import it.igesa.dto.ConfigGeneralDTO;
import it.igesa.domaine.ConfigGenerale;
import it.igesa.enumerations.ErrorCode;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.services.Iconfiguration;
import it.igesa.repository.IconfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Wassim Hachaani
 *
 */

@Service
public class ConfigurationImp implements Iconfiguration {

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
