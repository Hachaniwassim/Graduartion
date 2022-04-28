package app.igesa.dto;

import app.igesa.entity.Auditable;
import app.igesa.entity.Category;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class CategoryDTO {
	private Long id ;
	private String image ;
	private String title ;
	private String description ;
	private String menuimage ;
	private String bannerimage ;
	private boolean status ;


	public static CategoryDTO fromEntity(Category category) {

		return CategoryDTO.builder()
				.id(category.getId())
				.description(category.getDescription())
				.bannerimage(category.getBannerimage())
				.menuimage(category.getMenuimage())
				.title(category.getTitle())
				.image(category.getImage())
				.status(category.isStatus())
				.build();
	}



	public static Category toEntity(CategoryDTO dto) {
		if (dto==null) {
			return null;
		}

		Category category = new Category();
		category.setId(dto.getId());
		category.setDescription(dto.getDescription());
		category.setStatus(dto.isStatus());
		category.setBannerimage(dto.getBannerimage());
		category.setMenuimage(dto.getMenuimage());
		category.setTitle(dto.getTitle());
		category.setImage(dto.getImage());
		return category;
	}
}
