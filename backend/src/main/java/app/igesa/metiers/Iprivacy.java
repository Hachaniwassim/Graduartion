package app.igesa.metiers;
import app.igesa.dto.PrivacyDTO;
import java.util.Collection;
import java.util.Optional;

public interface Iprivacy {
    public PrivacyDTO updateByEntreprise (PrivacyDTO p,Long id_entreprise);

    Collection<PrivacyDTO> getprivacy(Long id_entreprise);

    public PrivacyDTO  findById(Long id_entreprise);
    public void delete(Long id);

    Optional<PrivacyDTO> findByEntrepriseId(Long id_entreprise);
}
