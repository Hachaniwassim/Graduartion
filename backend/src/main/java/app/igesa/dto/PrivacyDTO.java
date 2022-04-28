package app.igesa.dto;

import app.igesa.entity.Auditable;
import app.igesa.entity.Privacy;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Column;
import java.util.Date;
@Builder
@Data
public class PrivacyDTO extends Auditable {
    private Long id ;

    @Column(name="title")
    private String title ;

    @Column(name="description")
    private String description ;

    protected Date createdDate;
    protected Date lastModifiedDate;
    //protected String createdBy;

    public static PrivacyDTO fromEntity(Privacy privacy) {
		/*if ( company == null) {
			return null;
		}*/
        return PrivacyDTO.builder()
                .id(privacy.getId())
                .description(privacy.getDescription())
                .title(privacy.getTitle())
                //.createdBy(company.getCreatedBy())
                .lastModifiedDate(privacy.getLastModifiedDate())
                .createdDate(privacy.getCreatedDate())
                .build();
    }

    public static Privacy toEntity(PrivacyDTO dto) {

		/*if (dto == null) {
			return null;
		}*/

        Privacy privacy = new Privacy();
        privacy.setId(dto.getId());
        privacy.setDescription(dto.getDescription());
        privacy.setTitle(dto.getTitle());
        privacy.setCreatedDate(dto.getCreatedDate());
        privacy.setLastModifiedDate(dto.getLastModifiedDate());
        return privacy;
    }

}

