package app.igesa.metiers;

import app.igesa.dto.CategoryDTO;
import java.util.Collection;
import java.util.Optional;


public interface Icategory {
    public CategoryDTO save (CategoryDTO c);
    public Collection<CategoryDTO > getAllByEntreprise( Long id);
    public CategoryDTO findById(Long id);
    public void delete(Long id);
    public void deleteImage(Long categoryId, String type);
}
