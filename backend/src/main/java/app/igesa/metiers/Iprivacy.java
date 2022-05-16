package app.igesa.metiers;

import app.igesa.dto.PrivacyDTO;

import java.util.Collection;

public interface Iprivacy {
    public PrivacyDTO save (PrivacyDTO p);
    public Collection<PrivacyDTO > view();
    public PrivacyDTO  findById(Long id);
    public void delete(Long id);
}
