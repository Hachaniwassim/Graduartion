package it.igesa.services;

import it.igesa.dto.pages.PageDTO;
import java.util.Collection;
import java.util.Optional;

public interface Ipages {
    public PageDTO save (PageDTO p);
    public Collection<PageDTO> view(Long id_enterprise);
    public Optional<PageDTO> findById(Long id);
    public void delete(Long id);
}
