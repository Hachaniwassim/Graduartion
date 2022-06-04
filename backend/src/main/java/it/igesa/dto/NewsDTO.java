package it.igesa.dto;

import it.igesa.domaine.Entreprise;
import it.igesa.domaine.Nwes;
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
public class NewsDTO {
    private Long id;
    @Column(columnDefinition = "text")
    private String title;
    @Column(columnDefinition = "text")
    private String htmlContent;
    protected Date createdDate;
    protected Date lastModifiedDate;
    private Long entrepriseId;


    public static NewsDTO fromEntity(Nwes nwes) {

        return NewsDTO.builder()
                .id(nwes.getId())
                .htmlContent(nwes.getHtmlContent())
                .title(nwes.getTitle())
                .lastModifiedDate(nwes.getLastModifiedDate())
                .createdDate(nwes.getCreatedDate())

                .entrepriseId(nwes.getEntreprise().getId())
                .build();
    }

    public static Nwes toEntity(NewsDTO dto) {

        Nwes nwes = new Nwes();
        nwes.setId(dto.getId());
        nwes.setHtmlContent(dto.getHtmlContent());
        nwes.setTitle(dto.getTitle());
        nwes.setCreatedDate(dto.getCreatedDate());
        nwes.setLastModifiedDate(dto.getLastModifiedDate());
        //===========================> Entreprise ===========================>
        Entreprise entreprise = new Entreprise();
        entreprise.setId(dto.getEntrepriseId());
        nwes.setEntreprise(entreprise);
        return nwes;

    }
}
