package it.igesa.services.implement;
import it.igesa.config.EmailService;
import it.igesa.domaine.PostRevendeur;
import it.igesa.dto.DelearsDTO;
import it.igesa.enumerations.ErrorCode;
import it.igesa.enumerations.RevendeursStatus;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.repository.IRevndeursRepository;
import it.igesa.services.IPostRvendeurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostRevendeurImp implements IPostRvendeurs {

    @Autowired
    IRevndeursRepository iRevndeursRepository;
    @Autowired
    EmailService emailService;

    @Override
    public Collection<DelearsDTO> view(Long id_entreprise) {
        return iRevndeursRepository.findByEntrepriseId(id_entreprise).stream()
                .map(DelearsDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DelearsDTO> findById(Long id) {
        if ( id == null) {
            return null ;
        }

        return  Optional.of(iRevndeursRepository.findById(id).map(DelearsDTO::fromEntity).orElseThrow(()->
                new ResourceNotFoundException(" No FormEntity with  Id = :: " +id+ " was founded {} ..!",
                        ErrorCode.FORMENTITY_NOT_FOUND)));

    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            return;
        }
        iRevndeursRepository.deleteById(id);
    }


    @Override
    public DelearsDTO save(DelearsDTO f) {
        f.setRevendeursStatus(RevendeursStatus.PENDING);
       PostRevendeur saved = iRevndeursRepository.save(DelearsDTO.toEntity((f)));
        return DelearsDTO.fromEntity(saved);
    }


    @Override
    public DelearsDTO updateStatusRevendeur(Long id, RevendeursStatus status) throws MessagingException {
        Optional<PostRevendeur> Data = iRevndeursRepository.findById(id);
        PostRevendeur saved = null;
        if (Data.isPresent()) {
            PostRevendeur form = Data.get();

            if (RevendeursStatus.ACCEPTED == status) {
                form.setRevendeursStatus(RevendeursStatus.REJECTED);
            }
            if (RevendeursStatus.REJECTED== status) {
                form.setRevendeursStatus(RevendeursStatus.PENDING);
            }
            if (RevendeursStatus.PENDING== status) {
                form.setRevendeursStatus(RevendeursStatus.ACCEPTED);

                emailService.sendContactConfirm( form.getEmail(),form.getCompanyname());
            }

            saved = iRevndeursRepository.save(form);
        }
        return DelearsDTO.fromEntity(saved);



    }
}
