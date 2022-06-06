package it.igesa.services.implement;
import it.igesa.dto.pages.PageDTO;
import it.igesa.domaine.pages.Pages;
import it.igesa.enumerations.ErrorCode;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.services.Ipages;
import it.igesa.repository.IentrepriseRepository;
import it.igesa.repository.pages.IpagesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Wassim Hachani
 *
 */
@Service
@NoArgsConstructor
@AllArgsConstructor
public class PagesImp  implements Ipages {
    @Autowired
    IpagesRepository ipagesRepository;
    @Autowired
    IentrepriseRepository ientrepriseRepository;
    private static final Logger log = LoggerFactory.getLogger(PagesImp.class);


    @Override
    public PageDTO save(PageDTO p) {

       Pages saved =ipagesRepository.save(PageDTO.toEntity(p));
        return PageDTO.fromEntity(saved);

    }

    @Override
    public Collection<PageDTO> view(Long id_entreprise) {
        return ipagesRepository.findByEntrepriseId(id_entreprise).stream()
                .map(PageDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PageDTO> findById(Long id) {
        if ( id == null) {
            log.error(" Page Id is NULL .. !!");
            return null ;
        }

        return  Optional.of(ipagesRepository.findById(id).map(PageDTO::fromEntity).orElseThrow(()->
                new ResourceNotFoundException(" No Page  with  Id = :: " +id+ " was founded {} ..!",
                        ErrorCode.PAGES_NOT_FOUND)));

    }


    @Override
    public void delete(Long id) {
        if ( id == null) {
            log.error(" Page ID IS NULL ");
            return;
        }
        ipagesRepository.deleteById(id);
    }


}
