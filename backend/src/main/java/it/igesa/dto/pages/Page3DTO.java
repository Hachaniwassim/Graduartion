package it.igesa.dto.pages;
import it.igesa.domaine.Entreprise;
import it.igesa.domaine.pages.Page3;
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
public class Page3DTO {

    private Long id ;
    @Column(columnDefinition="text")
    private String title ;
    @Column(columnDefinition="text")
    private String htmlContent;
    private Long entrepriseId;
    protected Date createdDate;
    protected Date lastModifiedDate;

    public static Page3DTO fromEntity(Page3 page) {

        return Page3DTO.builder()
                .id(page.getId())
                .htmlContent(page.getHtmlContent())
                .title(page.getTitle())
                .lastModifiedDate(page.getLastModifiedDate())
                .entrepriseId(page.getEntreprise().getId())
                .createdDate(page.getCreatedDate())
                .build();
    }

    public static Page3 toEntity(Page3DTO dto) {

        Page3 page = new Page3();
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

