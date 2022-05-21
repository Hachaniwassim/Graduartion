package app.igesa.dto;
import app.igesa.entity.Category;
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
	protected Date lastModifiedDate;
	private boolean status ;


	public static CategoryDTO fromEntity(Category category) {

		return CategoryDTO.builder()
				.id(category.getId())
				.description(category.getDescription())
				.bannerimage(category.getBannerimage())
				.menuimage(category.getMenuimage())
				.title(category.getTitle())
				.image(category.getImage())
				.lastModifiedDate(category.getLastModifiedDate())
				.createdDate(category.getCreatedDate())
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
		return category;
	}
}
