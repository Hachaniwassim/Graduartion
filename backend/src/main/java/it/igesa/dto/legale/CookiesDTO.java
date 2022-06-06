package it.igesa.dto.legale;

import it.igesa.domaine.Cookies;
import it.igesa.domaine.Entreprise;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Column;
import java.util.Date;

/**
 *
 * @author Tarchoun Abir
 *
 */

@Builder
@Data
public class CookiesDTO {

    private Long id ;
    @Column(columnDefinition="text")
    private String title ;
    @Column(columnDefinition="text")
    private String htmlContent;
    private Long entrepriseId;
    protected Date createdDate;
    protected Date lastModifiedDate;

    public static CookiesDTO fromEntity(Cookies cookie) {

        return CookiesDTO.builder()
                .id(cookie.getId())
                .htmlContent(cookie.getHtmlContent())
                .title(cookie.getTitle())
                .lastModifiedDate(cookie.getLastModifiedDate())
                .entrepriseId(cookie.getEntreprise().getId())
                .createdDate(cookie.getCreatedDate())
                .build();
    }

    public static Cookies toEntity(CookiesDTO dto) {

        Cookies cookie = new Cookies();
        cookie.setId(dto.getId());
        cookie.setHtmlContent(dto.getHtmlContent());
        cookie.setTitle(dto.getTitle());
        cookie.setCreatedDate(dto.getCreatedDate());
        cookie.setLastModifiedDate(dto.getLastModifiedDate());
        //===========================> Entreprise
        Entreprise entreprise = new Entreprise();
        entreprise.setId(dto.getEntrepriseId());
        cookie.setEntreprise(entreprise);
        return cookie;
    }


}
