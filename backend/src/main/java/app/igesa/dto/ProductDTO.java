package app.igesa.dto;

/**
 * @author Tarchoun Abir
 */

import app.igesa.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.List;
/**
 * @author Tarchoun Abir
 */



@Builder
@Data
public class ProductDTO {
	private Long id ;
	private String image ;
	private String title ;
	private String detailimage ;
	private String note ;
	private String name ;
	private TagsDTO tags ;
	protected Date createdDate;
	protected Date lastModifiedDate;
	protected String createdBy;
	private  int consultationNumber ;
	private CategoryDTO category;
	private Long entrepriseId;
	private Long categorieId;
	private Long tagsId;
	private EntrepriseDTO entreprise ;

	public static ProductDTO fromEntity(Product product) {
		if ( product == null) {
			return null;
		}
		return ProductDTO.builder()
				.id(product.getId())
				.image(product.getImage())
				.title(product.getTitle())
				.detailimage(product.getDetailimage())
				.note(product.getNote())
				.name(product.getName())
				.consultationNumber(product.getConsultationNumber())
				.tags(TagsDTO.fromEntity(product.getTags()))
				.category(CategoryDTO.fromEntity(product.getCategory()))
				.lastModifiedDate(product.getLastModifiedDate())
				.createdDate(product.getCreatedDate())
				.entreprise(EntrepriseDTO.fromEntity(product.getEntreprise()))
				.build();
	}

	public static Product toEntity(ProductDTO dto) {

		if (dto == null) {
			return null;
		}

		 Product product = new Product();
		 product.setId(dto.getId());
		 product.setImage(dto.getImage()) ;
	     product.setTitle(dto.getTitle());
		 product.setNote(dto.getNote());
		 product.setName(dto.getName());
		 product.setConsultationNumber(dto.getConsultationNumber());
		 product.setDetailimage(dto.getDetailimage());
		 product.setCreatedDate(dto.getCreatedDate());
		 product.setLastModifiedDate(dto.getLastModifiedDate());
		 //tags
		 Tags tag = new Tags();
		 tag.setId(dto.getTagsId());
		 product.setTags(tag);
		 // entreprise
		 Entreprise entreprise= new Entreprise();
		 entreprise.setId(dto.getEntrepriseId());
		 product.setEntreprise(entreprise);
		 //category
		 Category  category = new Category();
		 category.setId(dto.getCategorieId());
		 product.setCategory(category);
		 return product;
	}



}
