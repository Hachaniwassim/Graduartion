package app.igesa.metiers;


import app.igesa.dto.NewsDTO;
import app.igesa.dto.PostDTO;

import java.util.Collection;

public interface INwes {

    public NewsDTO save (NewsDTO p);
    public Collection<NewsDTO> view(Long id_entreprise);
    public NewsDTO findById(Long id);
    public void delete(Long id);
}
