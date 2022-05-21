package app.igesa.metiers;
import app.igesa.dto.PrivacyDTO;
import java.util.Collection;

public interface Iprivacy {
    public PrivacyDTO updateByEntreprise (PrivacyDTO p);
    public Collection<PrivacyDTO >  getByEntreprise();
    public PrivacyDTO  findById(Long id);
    public void delete(Long id);
}
