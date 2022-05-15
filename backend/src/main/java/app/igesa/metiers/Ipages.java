package app.igesa.metiers;

import app.igesa.dto.PageDTO;
import java.util.Collection;
import java.util.Optional;

public interface Ipages {
    public PageDTO save (PageDTO p);
    public Collection<PageDTO> view();
    public Optional<PageDTO> findById(Long id);
    public void delete(Long id);
    public PageDTO  update(PageDTO p, Long id);
}
