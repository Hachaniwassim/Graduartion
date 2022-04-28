package app.igesa.metiers;
import app.igesa.dto.TagsDTO;
import app.igesa.entity.Tags;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.repository.ItagsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class TagsImp implements Itags{


    @Autowired
    ItagsRepository itagsRepository;
    private static final Logger log = LoggerFactory.getLogger(TagsImp.class);


    @Override
    public TagsDTO save(TagsDTO p) {
       /* List<String> errors = TagsValidator.validate(p);
        if (p.getId()!= null) {

        }
        if(!errors.isEmpty()) {
            log.error("Tags not valid !" ,p);
            throw new InvalideEntityException("Tags not valid !", ErrorCode.TAGS_NOT_VALID,errors);}*/


        Tags saved =itagsRepository.save(TagsDTO.toEntity(p));
        return TagsDTO.fromEntity(saved);
    }

    @Override
    public Collection<TagsDTO> view() {
        return itagsRepository.findAll().stream()
                .map(TagsDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TagsDTO> findById(Long id) {
        if ( id == null) {
            log.error(" Tags Id is NULL .. !!");
            return null ;
        }

        return  Optional.of(itagsRepository.findById(id).map(TagsDTO::fromEntity).orElseThrow(()->
                new ResourceNotFoundException(" No Tags  with  Id = :: " +id+ " was founded {} ..!",
                        ErrorCode.TAGS_NOT_FOUND)));
    }

    @Override
    public void delete(Long id) {
        if ( id == null) {
            log.error(" Tags ID IS NULL ");
            return;
        }
        itagsRepository.deleteById(id);

    }

    @Override
    public TagsDTO update(TagsDTO p, Long id) {
        return null;
    }
}
