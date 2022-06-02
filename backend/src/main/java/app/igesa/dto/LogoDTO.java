package app.igesa.dto;
import app.igesa.entity.BrandLogo;
import app.igesa.entity.Category;
import app.igesa.entity.Entreprise;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Builder
@Data
public class BrandDTO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;
    private String image ;
    @Type(type = "org.hibernate.type.TextType")
    private String description;
    protected Date createdDate;
    protected Date lastModifiedDate;
    private Long enterpriseId;


    public static BrandDTO fromEntity(BrandLogo brandLogo) {

        return BrandDTO.builder()
                .id(brandLogo.getId())
                .description(brandLogo.getDescription())
                .image(brandLogo.getImage())
                .lastModifiedDate(brandLogo.getLastModifiedDate())
                .createdDate(brandLogo.getCreatedDate())
                .enterpriseId(brandLogo.getEntreprise().getId())
                .build();
    }



    public static BrandLogo  toEntity(BrandDTO dto) {
        if (dto==null) {
            return null;
        }

       BrandLogo brandLogo = new BrandLogo();
        brandLogo.setId(dto.getId());
        brandLogo.setDescription(dto.getDescription());
        brandLogo.setCreatedDate(dto.getCreatedDate());
        brandLogo.setLastModifiedDate(dto.getLastModifiedDate());
        brandLogo.setImage(dto.getImage());
        //===========================> Entreprise
        Entreprise entreprise = new Entreprise();
        entreprise.setId(dto.getEnterpriseId());
        brandLogo.setEntreprise(entreprise);
        return brandLogo;
    }
}


