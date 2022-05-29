package app.igesa.metiers;
import app.igesa.dto.FormDTO;
import java.util.Collection;
import java.util.Optional;

public interface IformEntity {
    public FormDTO save(FormDTO f);
    public Collection<FormDTO> view(Long id_entreprise);
    public Optional<FormDTO> findById(Long id);
    // public FormDTO update(Long id, FormDTO f);
    public void delete(Long id);
}
