package app.igesa.metiers;

import app.igesa.dto.CookiesDTO;
import java.util.Collection;

public interface Icookies {
    public Collection<CookiesDTO > getCookies();
    CookiesDTO updateCookies(CookiesDTO c);
    public CookiesDTO findById(Long id);
}
