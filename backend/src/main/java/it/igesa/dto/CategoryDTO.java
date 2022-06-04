package it.igesa.dto;
import it.igesa.domaine.Category;
import it.igesa.domaine.Entreprise;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Tarchoun Abir
 */

@Data
@Builder
public class CategoryDTO {
	private Long id ;
	private String image ;
	private String title ;
	private String description ;
	private String menuimage ;
	private String bannerimage ;
	protected Date createdDate;
	private String subtitle;
	protected Date lastModifiedDate;
	private Long enterpriseId;


	public static CategoryDTO fromEntity(Category category) {

		return CategoryDTO.builder()
				.id(category.getId())
				.description(category.getDescription())
				.bannerimage(category.getBannerimage())
				.menuimage(category.getMenuimage())
				.title(category.getTitle())
				.image(category.getImage())
				.subtitle(category.getSubtitle())
				.lastModifiedDate(category.getLastModifiedDate())
				.createdDate(category.getCreatedDate())
				.enterpriseId(category.getEnterprise().getId())
				.build();
	}



	public static Category toEntity(CategoryDTO dto) {
		if (dto==null) {
			return null;
		}

		Category category = new Category();
		category.setId(dto.getId());
		category.setDescription(dto.getDescription());
		category.setBannerimage(dto.getBannerimage());
		category.setMenuimage(dto.getMenuimage());
		category.setTitle(dto.getTitle());
		category.setCreatedDate(dto.getCreatedDate());
		category.setLastModifiedDate(dto.getLastModifiedDate());
		category.setImage(dto.getImage());
		category.setSubtitle(dto.getSubtitle());
		//===========================> Entreprise
		Entreprise entreprise = new Entreprise();
		entreprise.setId(dto.getEnterpriseId());
		category.setEnterprise(entreprise);
		return category;
	}
}
