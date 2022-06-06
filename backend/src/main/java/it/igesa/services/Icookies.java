package it.igesa.services;

import it.igesa.dto.legale.CookiesDTO;

import java.util.Collection;

public interface Icookies {
    CookiesDTO updateCookies(CookiesDTO c);

    CookiesDTO findByEntrepriseId(Long id_entreprise);

    Collection<CookiesDTO> getCookiesByEntrepriseId(Long id_entreprise);

    CookiesDTO findById(Long id);
}