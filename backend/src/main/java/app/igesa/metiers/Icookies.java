package app.igesa.metiers;

import app.igesa.dto.CookiesDTO;
import java.util.Collection;

public interface Icookies {
    public Collection<CookiesDTO > viewCookies();
    CookiesDTO saveCookies(CookiesDTO c);
    public CookiesDTO findById(Long id);
    public void deleteCookies (Long id);
}
