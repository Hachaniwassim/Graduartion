package app.igesa.metiers;
import app.igesa.dto.Page1DTO;
import java.util.Collection;

/**
 * @author Tarchoun Abir
 */
public interface Ipage1 {
    public Page1DTO save (Page1DTO  p);
    public Collection<Page1DTO  > view();
    public Page1DTO  findById(Long id);
    public void delete(Long id);
}
