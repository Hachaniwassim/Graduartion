package it.igesa.services;
import it.igesa.dto.DelearsDTO;
import it.igesa.enumerations.RevendeursStatus;
import javax.mail.MessagingException;
import java.util.Collection;
import java.util.Optional;

public interface IPostRvendeurs {
    public DelearsDTO save(DelearsDTO f);
    public Collection<DelearsDTO> view(Long id_entreprise);
    public Optional<DelearsDTO> findById(Long id);
    public void delete(Long id);
    public DelearsDTO updateStatusRevendeur(Long id, RevendeursStatus status) throws MessagingException;

}
