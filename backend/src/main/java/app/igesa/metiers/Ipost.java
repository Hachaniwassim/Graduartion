package app.igesa.metiers;

import app.igesa.dto.PostDTO;
import java.util.Collection;
import java.util.Optional;

public interface Ipost {
    public PostDTO save (PostDTO p);
    public Collection<PostDTO> view();
    public PostDTO findById(Long id);
    public void delete(Long id);
    public PostDTO update(PostDTO p, Long id);
}
