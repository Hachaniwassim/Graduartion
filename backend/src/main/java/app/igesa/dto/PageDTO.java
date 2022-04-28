package app.igesa.dto;


import app.igesa.entity.Groupe;
import app.igesa.entity.Pages;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PageDTO {
	private Long id ;
	private String title ;
	private String description ;
	private boolean published ;
	private EntrepriseDTO entreprise;
	protected Date createdDate;
	protected Date lastModifiedDate;
	protected String createdBy;
	//private PageTranslate transInfo ;




	public static PageDTO fromEntity(Pages page) {
		if (page==null) {

			return null;
		}
		return PageDTO.builder()
				.id(page.getId())
				.title(page.getTitle())
				.description(page.getDescription())
				.published(page.isPublished())
				.entreprise(EntrepriseDTO.fromEntity(page.getEntreprise()))
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
		page.setPublished(page.isPublished());
		page.setEntreprise(EntrepriseDTO.toEntity(dto.getEntreprise()));
		return page;
	}

}
