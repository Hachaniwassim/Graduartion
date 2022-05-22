package app.igesa.dto;

import app.igesa.entity.Entreprise;
import app.igesa.entity.pages.Pages;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author Tarchoun Abir
 */

@Data
@Builder
public class PageDTO {
	private Long id ;
	@Column(columnDefinition="text")
	private String title ;
	@Column(columnDefinition="text")
	private String description ;
	protected Date createdDate;
	protected Date lastModifiedDate;
	private Long entrepriseId ;




	public static PageDTO fromEntity(Pages page) {
		if (page==null) {

			return null;
		}
		return PageDTO.builder()
				.id(page.getId())
				.title(page.getTitle())
				.description(page.getDescription())
				.lastModifiedDate(page.getLastModifiedDate())
				.entrepriseId(page.getEntreprise().getId())
				.createdDate(page.getCreatedDate())
				.build();
	}

	public static Pages toEntity(PageDTO dto) {
		if (dto==null) {
			return null;
		}

		Pages page = new Pages();
		page.setId(dto.getId());
		page.setTitle(dto.getTitle());
		page.setDescription(dto.getDescription());
		page.setLastModifiedDate(dto.getLastModifiedDate());
		page.setCreatedDate(dto.getCreatedDate());
		//===========================> Entreprise ===========================>
		Entreprise entreprise = new Entreprise();
		entreprise.setId(dto.getEntrepriseId());
		page.setEntreprise(entreprise);
		return page;
	}

}
