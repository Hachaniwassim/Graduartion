package it.igesa.dto;
import it.igesa.domaine.Logo;
import it.igesa.domaine.Entreprise;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Builder
@Data
public class LogoDTO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;
    private String image ;
    @Type(type = "org.hibernate.type.TextType")
    private String description;
    protected Date createdDate;
    protected Date lastModifiedDate;
    private Long enterpriseId;


    public static LogoDTO fromEntity(Logo logo) {

        return LogoDTO.builder()
                .id(logo.getId())
                .description(logo.getDescription())
                .image(logo.getImage())
                .lastModifiedDate(logo.getLastModifiedDate())
                .createdDate(logo.getCreatedDate())
                .enterpriseId(logo.getEntreprise().getId())
                .build();
    }



    public static Logo toEntity(LogoDTO dto) {
        if (dto==null) {
            return null;
        }

       Logo logo = new Logo();
        logo.setId(dto.getId());
        logo.setDescription(dto.getDescription());
        logo.setCreatedDate(dto.getCreatedDate());
        logo.setLastModifiedDate(dto.getLastModifiedDate());
        logo.setImage(dto.getImage());
        //===========================> Entreprise
        Entreprise entreprise = new Entreprise();
        entreprise.setId(dto.getEnterpriseId());
        logo.setEntreprise(entreprise);
        return logo;
    }
}


