package app.igesa.metiers;

import app.igesa.dto.LanguageDTO;
import java.util.Collection;
import java.util.Optional;

public interface Ilanguage {
    public LanguageDTO save (LanguageDTO L);
    public Collection<LanguageDTO> view();
    public LanguageDTO findById(Long id);
    public void delete(Long id);
    public LanguageDTO update(LanguageDTO L, Long id);
}
