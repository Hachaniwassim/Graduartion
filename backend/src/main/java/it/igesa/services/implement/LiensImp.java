package it.igesa.services.implement;
import it.igesa.dto.LiensDTO;
import it.igesa.domaine.Liens;
import it.igesa.enumerations.ErrorCode;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.services.Iliens;
import it.igesa.repository.IliensRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class LiensImp  implements Iliens {

    @Autowired
    IliensRepository iliensRepository;

    private static final Logger log = LoggerFactory.getLogger(LiensImp.class);

    @Override
    public LiensDTO save(LiensDTO p) {
        Liens saved = iliensRepository.save(LiensDTO.toEntity(p));
        return  LiensDTO.fromEntity(saved);
    }




    @Override
    public Collection<LiensDTO>view(Long id_entreprise) {
        log.debug("HTTP GET ALL {} ..");
        return iliensRepository.findByEntrepriseId(id_entreprise).stream()
                .map(LiensDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public LiensDTO findById(Long id) {
        if ( id == null) {
            log.error(" Language  Id is NULL .. !!");
            return null ;
        }

        return  iliensRepository.findById(id).map(LiensDTO::fromEntity).orElseThrow(()->
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
        iliensRepository.deleteById(id);
    }




}
