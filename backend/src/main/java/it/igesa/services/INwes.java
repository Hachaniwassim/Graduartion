package it.igesa.services;
import it.igesa.dto.NewsDTO;

import java.util.Collection;

public interface INwes {

    public NewsDTO save (NewsDTO p);
    public Collection<NewsDTO> view(Long id_entreprise);
    public NewsDTO findById(Long id);
    public void delete(Long id);
}
