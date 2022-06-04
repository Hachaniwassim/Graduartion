package it.igesa.services;
import it.igesa.dto.TagsDTO;
import java.util.Collection;

/**
 * Tarchoun Abir
 */
public interface Itags {

    public TagsDTO save (TagsDTO  p);
    public Collection<TagsDTO> view(Long id_entreprise);
    public void delete(Long id);

   // public Optional<TagsDTO> findById(Long id);
    //public TagsDTO update(TagsDTO p, Long id);
}
