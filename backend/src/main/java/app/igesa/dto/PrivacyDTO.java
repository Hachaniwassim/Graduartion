package app.igesa.dto;

import app.igesa.entity.Privacy;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Column;
import java.util.Date;


@Builder
@Data
public class PrivacyDTO  {
    private Long id ;

    @Column(name="title")
    private String title ;
    @Column(name="description")
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
                .build();
    }

    public static Privacy toEntity(PrivacyDTO dto) {

		/*if (dto == null) {
			return null;
		}*/

        Privacy privacy = new Privacy();
        privacy.setId(dto.getId());
        privacy.setHtmlContent(dto.getHtmlContent());
        privacy.setTitle(dto.getTitle());
        privacy.setCreatedDate(dto.getCreatedDate());
        privacy.setLastModifiedDate(dto.getLastModifiedDate());
        return privacy;
    }

}

