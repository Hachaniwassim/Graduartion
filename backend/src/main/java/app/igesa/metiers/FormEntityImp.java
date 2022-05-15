package app.igesa.metiers;

import app.igesa.dto.FormDTO;
import app.igesa.entity.FormEntity;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.exceptions.ResourceNotFoundException;
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


@Service
@AllArgsConstructor
@NoArgsConstructor
public class FormEntityImp implements IformEntity {

    @Autowired
    IformEntityRepository iformEntityRepository;
    private static final Logger log = LoggerFactory.getLogger(FormEntityImp.class);


    @Override
    public FormDTO save(FormDTO f) {
        List<String> errors = FromValidator.validate(f);
        if (f.getId()!= null) {

        }
        if(!errors.isEmpty()) {
            log.error("Form not valid !" ,f);
            throw new InvalideEntityException("FormEntity not valid !", ErrorCode.FORM_NOT_VALID,errors);}

        FormEntity saved =iformEntityRepository.save(FormDTO.toEntity(f));
        return FormDTO.fromEntity(saved);
    }

    @Override
    public Collection<FormDTO> view() {
        return iformEntityRepository.findAll().stream()
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
