package it.igesa.services.logo;
import it.igesa.dto.LogoDTO;
import java.util.Collection;

public interface ILogo {

    public LogoDTO save (LogoDTO brand);
    public Collection<LogoDTO> getAllByEntreprise(Long enterprise_id);
    public void delete(Long id);
}
