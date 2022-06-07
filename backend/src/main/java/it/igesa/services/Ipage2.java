package it.igesa.services;
import it.igesa.dto.pages.Page2DTO;
import java.util.Collection;

/**
 * @author Tarchoun Abir
 */
public interface Ipage2 {
    public Page2DTO save (Page2DTO p);
    public Collection<Page2DTO> view(Long id_enterprise);
    public Page2DTO  findById(Long id);
    public void delete(Long id);
}
