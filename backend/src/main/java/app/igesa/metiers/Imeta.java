package app.igesa.metiers;
import app.igesa.dto.MetaDTO;
import app.igesa.dto.ProductDTO;

import java.util.Collection;
import java.util.Optional;

public interface Imeta {
    public MetaDTO save (MetaDTO m);
    public Collection<MetaDTO> view();
    public Optional<MetaDTO> findById(Long id);
    public void delete(Long id);
    public MetaDTO  update(MetaDTO m, Long id);
}
