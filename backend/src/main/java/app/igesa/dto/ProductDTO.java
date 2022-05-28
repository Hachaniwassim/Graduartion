package app.igesa.dto;
import app.igesa.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Tarchoun Abir
 *
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
	protected Date createdDate;
	protected Date lastModifiedDate;
	protected String createdBy;
	private  int consultationNumber ;
	private CategoryDTO category;
	private Long entrepriseId;
	private Long categorieId;
	private Set<Tags> tags;
	//private Long tagsId;
    //private TagsDTO tags ;


	public static ProductDTO fromEntity(Product product) {
		if ( product == null) {
			return null;
		}
		return ProductDTO.builder()
				.id(product.getId())
				.image(product.getImage())
				.title(product.getTitle())
				.detailimage(product.getDetailsimage())
				.note(product.getNote())
				.name(product.getName())
				.lastModifiedDate(product.getLastModifiedDate())
				.createdDate(product.getCreatedDate())
				.entrepriseId(product.getEntreprise().getId())
				.categorieId(product.getCategory().getId())
				.tags(product.getTags())
				//.tagsId(product.getTags().getId())
				//.tags(TagsDTO.fromEntity(product.getTags()))
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
		 product.setDetailsimage(dto.getDetailimage());
		 product.setCreatedDate(dto.getCreatedDate());
		 product.setLastModifiedDate(dto.getLastModifiedDate());
		 product.setTags(dto.getTags());
		 //=================> entreprise
		 Entreprise entreprise= new Entreprise();
		 entreprise.setId(dto.getEntrepriseId());
		 product.setEntreprise(entreprise);
		 //==================> category
		 Category  category = new Category();
		 category.setId(dto.getCategorieId());
		 product.setCategory(category);

		//=================> tags
		// Tags tag = new Tags();
		// tag.setId(dto.getTagsId());
		// product.setTags(tag);

		 return product;
	}



}
