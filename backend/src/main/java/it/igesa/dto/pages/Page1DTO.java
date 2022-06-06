package it.igesa.dto.pages;
import it.igesa.domaine.Entreprise;
import it.igesa.domaine.pages.Page1;
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
public class Page1DTO {

    private Long id ;
    @Column(columnDefinition="text")
    private String title ;
    @Column(columnDefinition="text")
    private String htmlContent;
    private Long entrepriseId;
    protected Date createdDate;
    protected Date lastModifiedDate;

    public static Page1DTO fromEntity(Page1 page1) {

        return Page1DTO.builder()
                .id(page1.getId())
                .htmlContent(page1.getHtmlContent())
                .title(page1.getTitle())
                .lastModifiedDate(page1.getLastModifiedDate())
                .entrepriseId(page1.getEntreprise().getId())
                .createdDate(page1.getCreatedDate())
                .build();
    }

    public static Page1 toEntity(Page1DTO dto) {

        Page1 page = new Page1();
        page.setId(dto.getId());
        page.setHtmlContent(dto.getHtmlContent());
        page.setTitle(dto.getTitle());
        page.setCreatedDate(dto.getCreatedDate());
        page.setLastModifiedDate(dto.getLastModifiedDate());
        //===========================> Entreprise ===========================>
        Entreprise entreprise = new Entreprise();
        entreprise.setId(dto.getEntrepriseId());
        page.setEntreprise(entreprise);
        return page;
    }


}
