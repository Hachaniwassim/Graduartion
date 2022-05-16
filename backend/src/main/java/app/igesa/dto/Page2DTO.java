package app.igesa.dto;

import app.igesa.entity.Cookies;
import app.igesa.entity.Page1;
import app.igesa.entity.Page2;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Tarchoun Abir
 */

@Builder
@Data
public class Page2DTO {

    private Long id ;
    private String title ;
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

        Page2 page = new Page2();
        page.setId(dto.getId());
        page.setHtmlContent(dto.getHtmlContent());
        page.setTitle(dto.getTitle());
        page.setCreatedDate(dto.getCreatedDate());
        page.setLastModifiedDate(dto.getLastModifiedDate());
        //Entreprise entreprise = new Entreprise();
        //entreprise.setId(dto.getEntrepriseId());
        //page.setEntreprise(entreprise);
        return page;
    }


}

