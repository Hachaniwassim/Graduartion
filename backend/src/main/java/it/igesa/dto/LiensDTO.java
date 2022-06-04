package it.igesa.dto;


import it.igesa.domaine.Entreprise;
import it.igesa.domaine.Liens;
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
public class LiensDTO {

    private Long id ;
    @Column(columnDefinition="text")
    private String title ;
    @Column(columnDefinition="text")
    private String htmlContent;
    private Long entrepriseId;
    protected Date createdDate;
    protected Date lastModifiedDate;

    public static LiensDTO fromEntity(Liens liens) {

        return LiensDTO.builder()
                .id(liens.getId())
                .htmlContent(liens.getHtmlContent())
                .title(liens.getTitle())
                .lastModifiedDate(liens.getLastModifiedDate())
                .entrepriseId(liens.getEntreprise().getId())
                .createdDate(liens.getCreatedDate())
                .build();
    }

    public static Liens toEntity(LiensDTO dto) {

        Liens liens = new Liens();
        liens.setId(dto.getId());
        liens.setHtmlContent(dto.getHtmlContent());
        liens.setTitle(dto.getTitle());
        liens.setCreatedDate(dto.getCreatedDate());
        liens.setLastModifiedDate(dto.getLastModifiedDate());
        //===========================> Entreprise ===========================>
        Entreprise entreprise = new Entreprise();
        entreprise.setId(dto.getEntrepriseId());
        liens.setEntreprise(entreprise);
        return  liens;
    }

}
