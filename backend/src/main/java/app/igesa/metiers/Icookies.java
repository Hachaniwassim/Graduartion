package app.igesa.metiers;

import app.igesa.dto.CookiesDTO;
import java.util.Collection;

public interface Icookies {
    CookiesDTO updateCookies(CookiesDTO c, Long id_entreprise);

    CookiesDTO findByEntrepriseId(Long id_entreprise);

    Collection<CookiesDTO> getCookiesByEntrepriseId(Long id_entreprise);

    CookiesDTO findById(Long id);
}