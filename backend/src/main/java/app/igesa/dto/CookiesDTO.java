package app.igesa.dto;
import app.igesa.entity.Cookies;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
@Builder
@Data
public class CookiesDTO {

    private Long id ;

    @Column(name="title")
    private String title ;

    @Column(name="description")
    private String htmlContent;

    protected Date createdDate;
    protected Date lastModifiedDate;

    public static CookiesDTO fromEntity(Cookies cookie) {

        return CookiesDTO.builder()
                .id(cookie.getId())
                .htmlContent(cookie.getHtmlContent())
                .title(cookie.getTitle())
                //.createdBy(company.getCreatedBy())
                .lastModifiedDate(cookie.getLastModifiedDate())
                .createdDate(cookie.getCreatedDate())
                .build();
    }

    public static Cookies toEntity(CookiesDTO dto) {

		/*if (dto == null) {
			return null;
		}*/

       Cookies cookie = new Cookies();
        cookie.setId(dto.getId());
        cookie.setHtmlContent(dto.getHtmlContent());
        cookie.setTitle(dto.getTitle());
        cookie.setCreatedDate(dto.getCreatedDate());
        cookie.setLastModifiedDate(dto.getLastModifiedDate());
        return cookie;
    }


}
