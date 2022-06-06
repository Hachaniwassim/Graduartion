package it.igesa.services.implement;

import it.igesa.config.EmailService;
import it.igesa.domaine.Groupe;
import it.igesa.dto.FormDTO;
import it.igesa.domaine.FormEntity;
import it.igesa.dto.GroupeDTO;
import it.igesa.enumerations.ContactStatus;
import it.igesa.enumerations.ErrorCode;
import it.igesa.enumerations.GroupStatus;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.services.Ientreprise;
import it.igesa.services.IformEntity;
import it.igesa.repository.IformEntityRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;

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

    @Autowired
    EmailService emailService;

    private static final Logger log = LoggerFactory.getLogger(FormEntityImp.class);


    @Override
    public FormDTO save(FormDTO f) {
           f.setContactStatus(ContactStatus.PENDING);
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


    /***
     *
     * @param id
     * @param status
     * @return contact status
     *
     */
    @Override
    public FormDTO updateStatusContact(Long id, ContactStatus status) throws MessagingException {
        log.debug( "<========================= Update account status ================================>");
            Optional<FormEntity> Data = iformEntityRepository.findById(id);
                FormEntity saved = null;
            if (Data.isPresent()) {
                FormEntity form = Data.get();

                if (ContactStatus.ACCEPTED == status) {
                    form.setContactstatus(ContactStatus.REJECTED);
                }
                if (ContactStatus.REJECTED== status) {
                    form.setContactstatus(ContactStatus.PENDING);
                }
                if (ContactStatus.PENDING== status) {
                    form.setContactstatus(ContactStatus.ACCEPTED);

                    emailService.sendContactConfirm( form.getEmail(),form.getCompanyname());
                }

                saved = iformEntityRepository.save(form);
            }
            return FormDTO.fromEntity(saved);



    }
}
