package it.igesa.services;
import it.igesa.dto.pages.Page3DTO;
import java.util.Collection;

/**
 * @author Tarchoun Abir
 */
public interface Ipage3 {
    public Page3DTO save (Page3DTO p);
    public Collection<Page3DTO> view(Long id_enterprise);
    public Page3DTO  findById(Long id);
    public void delete(Long id);
}
