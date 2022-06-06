package it.igesa.dto.pages;

import it.igesa.domaine.Entreprise;
import it.igesa.domaine.pages.Page2;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author Tarchoun Abir
 */

@Builder
@Data
public class Page2DTO {

    private Long id ;
    @Column(columnDefinition="text")
    private String title ;
    @Column(columnDefinition="text")
    private String htmlContent;
    private Long entrepriseId;
    protected Date createdDate;
    protected Date lastModifiedDate;

    public static Page2DTO fromEntity(Page2 page) {

        return Page2DTO.builder()
                .id(page.getId())
                .htmlContent(page.getHtmlContent())
                .title(page.getTitle())
                .lastModifiedDate(page.getLastModifiedDate())
                .entrepriseId(page.getEntreprise().getId())
                .createdDate(page.getCreatedDate())
                .build();
    }

    public static Page2 toEntity(Page2DTO dto) {

        Page2 pages = new Page2();
        pages.setId(dto.getId());
        pages.setHtmlContent(dto.getHtmlContent());
        pages.setTitle(dto.getTitle());
        pages.setCreatedDate(dto.getCreatedDate());
        pages.setLastModifiedDate(dto.getLastModifiedDate());
        //===========================> Entreprise ===========================>
        Entreprise entreprise = new Entreprise();
        entreprise.setId(dto.getEntrepriseId());
        pages.setEntreprise(entreprise);
        return pages;
    }


}

