package it.igesa.dto;
import it.igesa.domaine.Category;
import it.igesa.domaine.Entreprise;
import it.igesa.domaine.Product;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

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
	@Column(columnDefinition="text")
	private String description ;
	private String Slug ;
	private String name ;
	protected Date createdDate;
	protected Date lastModifiedDate;
	@Column(columnDefinition="text")
	private String caracteristique;
	@Column(columnDefinition="text")
	private String requirements;
	private Long entrepriseId;
	private Long categorieId;




	public static ProductDTO fromEntity(Product product) {
		if ( product == null) {
			return null;
		}
		return ProductDTO.builder()
				.id(product.getId())
				.image(product.getImage())
				.title(product.getTitle())
				.Slug(product.getSlug())
				.name(product.getName())
				.lastModifiedDate(product.getLastModifiedDate())
				.createdDate(product.getCreatedDate())
				.caracteristique(product.getCaracteristique())
				.description(product.getDescription())
				.requirements(product.getRequirements())
				.categorieId(product.getCategory().getId())
				.entrepriseId(product.getEntreprise().getId())

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
		 product.setSlug(dto.getSlug());
		 product.setName(dto.getName());
		 product.setDescription(dto.getDescription());
		 product.setCreatedDate(dto.getCreatedDate());
		 product.setLastModifiedDate(dto.getLastModifiedDate());
	 	 product.setCaracteristique(dto.getCaracteristique());
		 product.setRequirements(dto.getRequirements());
		 //=================> entreprise
		 Entreprise entreprise= new Entreprise();
		 entreprise.setId(dto.getEntrepriseId());
		 product.setEntreprise(entreprise);
		 //==================> category
		 Category category = new Category();
		 category.setId(dto.getCategorieId());
		 product.setCategory(category);

		//=================> tags
		// Tags tag = new Tags();
		// tag.setId(dto.getTagsId());
		// product.setTags(tag);

		 return product;
	}



}
