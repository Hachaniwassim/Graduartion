package it.igesa.services;

import it.igesa.dto.LanguageDTO;
import java.util.Collection;

public interface Ilanguage {
    public LanguageDTO save (LanguageDTO L);
    public Collection<LanguageDTO> view(Long id_entreprise);
    public LanguageDTO findById(Long id);
    public void delete(Long id);
}
