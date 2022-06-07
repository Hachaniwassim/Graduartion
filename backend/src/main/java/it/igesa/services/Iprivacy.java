package it.igesa.services;
import it.igesa.dto.legale.PrivacyDTO;
import java.util.Collection;

public interface Iprivacy {
    public PrivacyDTO updateByEntreprise (PrivacyDTO p);

    Collection<PrivacyDTO> FindByEntrepriseId(Long id_entreprise);

    public PrivacyDTO  findById(Long id_entreprise);
    public void delete(Long id);

}
