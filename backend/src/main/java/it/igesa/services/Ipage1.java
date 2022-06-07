package it.igesa.services;
import it.igesa.dto.pages.Page1DTO;
import java.util.Collection;

/**
 * @author Tarchoun Abir
 */
public interface Ipage1 {
    public Page1DTO save (Page1DTO  p);
    public Collection<Page1DTO> view(Long id_enterprise);
    public Page1DTO findById(Long id);
    public void delete(Long id);
}
