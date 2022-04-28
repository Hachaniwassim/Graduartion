package app.igesa.metiers;

import app.igesa.dto.ConfigGeneralDTO;
import app.igesa.entity.ConfigGenerale;
import app.igesa.entity.Entreprise;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.exceptions.ResourceNotFoundException;
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
        List<String> errors = ConfigValidator.validateConfig(c);
        if (!errors.isEmpty()) {
            log.error("Config not valid !", c);
            throw new InvalideEntityException("Confignot valid !", ErrorCode.CONFIG_NOT_VALID, errors);
        }

        Optional<Entreprise> entreprise = ientrepriseRepository.findById(c.getEntreprise().getId());

        if (entreprise == null) {

            log.warn("Entreprise with id =  was not found  in the database", c.getEntreprise().getId());
            throw new ResourceNotFoundException("Groupe not Found with Id  = " + c.getEntreprise().getId() + " In the data base", ErrorCode.CONFIGURATION_NOT_FOUND);
        }
        ConfigGenerale saved =iconfigRepository.save(ConfigGeneralDTO.toEntity(c));
        return ConfigGeneralDTO.fromEntity(saved);
    }

    @Override
    public Collection<ConfigGeneralDTO> view() {
        return iconfigRepository.findAll().stream()
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

    @Override
    public ConfigGeneralDTO update(ConfigGeneralDTO c, Long id) {
        return null;
    }


}
