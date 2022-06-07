package it.igesa.dto.legale;

import it.igesa.domaine.Entreprise;
import it.igesa.domaine.Privacy;
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
public class PrivacyDTO  {
    private Long id ;
    private Long entrepriseId;
    @Column(columnDefinition="text")
    private String title ;
    @Column(columnDefinition="text")
    private String htmlContent ;
    protected Date createdDate;
    protected Date lastModifiedDate;

    public static PrivacyDTO fromEntity(Privacy privacy) {

        return PrivacyDTO.builder()
                .id(privacy.getId())
                .htmlContent(privacy.getHtmlContent())
                .title(privacy.getTitle())
                .lastModifiedDate(privacy.getLastModifiedDate())
                .createdDate(privacy.getCreatedDate())
                 .entrepriseId(privacy.getEntreprise().getId())
                .build();
    }

    public static Privacy toEntity(PrivacyDTO dto) {

        Privacy privacy = new Privacy();
        privacy.setId(dto.getId());
        privacy.setHtmlContent(dto.getHtmlContent());
        privacy.setTitle(dto.getTitle());
        privacy.setCreatedDate(dto.getCreatedDate());
        privacy.setLastModifiedDate(dto.getLastModifiedDate());
        //=================> entreprise
        Entreprise entreprise = new Entreprise();
        entreprise.setId(dto.getEntrepriseId());
        privacy.setEntreprise(entreprise);
        return privacy;
    }

}

