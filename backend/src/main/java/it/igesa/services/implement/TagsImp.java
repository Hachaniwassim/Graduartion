package it.igesa.services.implement;
import it.igesa.dto.TagsDTO;
import it.igesa.domaine.Tags;
import it.igesa.services.Ientreprise;
import it.igesa.services.Itags;
import it.igesa.repository.ItagsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 * @author  Tarchoun Abir
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Service
public class TagsImp implements Itags {

    @Autowired
    Ientreprise ientrepriseService;

    @Autowired
    ItagsRepository itagsRepository;

    private static final Logger log = LoggerFactory.getLogger(TagsImp.class);


    @Override
    public TagsDTO save(TagsDTO p) {
       /*Tags tags = new Tags();
       if (p.getId()!=null){
         tags = itagsRepository.findById(p.getId()).orElseThrow(IllegalAccessError::new);
       }*/
       // tags.setEntreprise(ientrepriseService.getCurrentEnterprise());
       // tags.setDescription(p.getDescription());
        Tags saved = itagsRepository.save(TagsDTO.toEntity(p));
        return TagsDTO.fromEntity(saved);

    }

    @Override
    public Collection<TagsDTO> view(Long id_entreprise) {
        return itagsRepository.findByEntrepriseId(id_entreprise).stream()
                .map(TagsDTO::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error(" Tags ID IS NULL ");
            return;
        }
        itagsRepository.deleteById(id);

    }

}
