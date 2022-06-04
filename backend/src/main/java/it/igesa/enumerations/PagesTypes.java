package it.igesa.enumerations;

/**
 *
 * @author Tarchoun Abir
 *
 */

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PagesTypes {
    ABOUT_US ,
    CONTACT,
    PRODUCT,
    HOME,
    CATEGORY,
    COOKIES,
    NEWS,
    REVENDEURS,
    ASSISTANCE,
    LINKS,
    INSCRIPTION,
    LOGIN,
    PRODUCTS,
    PAGE_1,
    PAGE_2,
    PAGE_3,
    FAQ;

    @JsonCreator
    public static PagesTypes fromString(String page) {
        return PagesTypes.valueOf(page.toUpperCase().trim());
    }
}
