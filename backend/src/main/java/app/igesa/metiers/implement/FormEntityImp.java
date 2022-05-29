package app.igesa.metiers.implement;

import app.igesa.dto.FormDTO;
import app.igesa.dto.Page2DTO;
import app.igesa.entity.FormEntity;
import app.igesa.entity.Page2;
import app.igesa.enumerations.ContactStatus;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.metiers.Ientreprise;
import app.igesa.metiers.IformEntity;
import app.igesa.repository.IformEntityRepository;
import app.igesa.validators.FromValidator;
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
 * @author Wassim Hachani
 *
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
public class FormEntityImp implements IformEntity {

    @Autowired
    IformEntityRepository iformEntityRepository;
    @Autowired
    Ientreprise ientrepriseService;
    private static final Logger log = LoggerFactory.getLogger(FormEntityImp.class);


    @Override
    public FormDTO save(FormDTO f) {
            FormEntity formEntity= new FormEntity();
            if (f.getId()!=null){
                formEntity = iformEntityRepository.findById(f.getId()).orElseThrow(IllegalAccessError::new);
            }
            formEntity.setEntreprise(ientrepriseService.getCurrentEnterprise());
            formEntity.setContactstatus(ContactStatus.PENDING);
            formEntity.setCompanyname(f.getCompanyname());
            formEntity.setName(f.getName());
            formEntity.setReferent(f.getReferent());
            formEntity.setAdresse(f.getAdresse());
            formEntity.setMobile(f.getMobile());
            formEntity.setFax(f.getFax());
            formEntity.setNationality(f.getNationality());
            FormEntity saved = iformEntityRepository.save(FormDTO.toEntity((f)));
            return FormDTO.fromEntity(saved);
    }

    @Override
    public Collection<FormDTO> view(Long id_entreprise) {
        return iformEntityRepository.findByEntrepriseId(id_entreprise).stream()
                .map(FormDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FormDTO> findById(Long id) {
        if ( id == null) {
            log.error(" FormEntity Id is NULL .. !!");
            return null ;
        }

        return  Optional.of(iformEntityRepository.findById(id).map(FormDTO::fromEntity).orElseThrow(()->
                new ResourceNotFoundException(" No FormEntity with  Id = :: " +id+ " was founded {} ..!",
                        ErrorCode.FORMENTITY_NOT_FOUND)));

    }

    @Override
    public void delete(Long id) {
        if ( id == null) {
            log.error(" FormEntity ID IS NULL ");
            return;
        }
        iformEntityRepository.deleteById(id);
    }
    }
