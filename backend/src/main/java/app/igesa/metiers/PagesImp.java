package app.igesa.metiers;
import app.igesa.dto.EntrepriseDTO;
import app.igesa.dto.PageDTO;
import app.igesa.entity.Entreprise;
import app.igesa.entity.Pages;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.repository.IentrepriseRepository;
import app.igesa.repository.IpagesRepository;
import app.igesa.validators.pageValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PagesImp  implements Ipages{
    @Autowired
    IpagesRepository ipagesRepository;
    @Autowired
    IentrepriseRepository ientrepriseRepository;
    private static final Logger log = LoggerFactory.getLogger(PagesImp.class);


    @Override
    public PageDTO save(PageDTO p) {
        List<String> errors = pageValidator.validate(p);
        if (p.getId()!= null) {

        }
        if(!errors.isEmpty()) {
            log.error("Page not valid !" ,p);
            throw new InvalideEntityException("page not valid !", ErrorCode.PAGES_NOT_VALID,errors);}

        Optional<Entreprise> entreprise = ientrepriseRepository.findById(p.getEntreprise().getId());

        if( entreprise== null) {

            log.warn("Entreprise with id =  was not found  in the database", p.getEntreprise().getId());
            throw new ResourceNotFoundException("Entreprise not Found with Id  = " + p.getEntreprise().getId()+ " In the data base" ,ErrorCode.ENTREPRISE_NOT_FOUND);
        }

        if(entreprise.isPresent()) {
            p.setEntreprise(EntrepriseDTO.fromEntity(entreprise.get()));
        }

       Pages saved =ipagesRepository.save(PageDTO.toEntity(p));
        return PageDTO.fromEntity(saved);

    }

    @Override
    public Collection<PageDTO> view() {
        return ipagesRepository.findAll().stream()
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

    @Override
    public PageDTO update(PageDTO p, Long id) {
        return null;
    }
}
