package app.igesa.metiers;
;
import app.igesa.dto.TagsDTO;
import java.util.Collection;
import java.util.Optional;

public interface Itags {

    public TagsDTO save (TagsDTO  p);
    public Collection<TagsDTO> view();
    public Optional<TagsDTO> findById(Long id);
    public void delete(Long id);
    public TagsDTO update(TagsDTO p, Long id);
}
