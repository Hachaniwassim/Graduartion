package app.igesa.dto;



import app.igesa.entity.Entreprise;
import app.igesa.entity.Meta;
import app.igesa.entity.Product;
import app.igesa.entity.Tags;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
	private EntrepriseDTO entreprise ;
	@JsonIgnore
	private List<MetaDTO> metas;

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
		 product.setTags(TagsDTO.toEntity(dto.getTags()));
		 product.setCategory(CategoryDTO.toEntity(dto.getCategory()));
		 product.setEntreprise(EntrepriseDTO.toEntity(dto.getEntreprise()));
		 product.setDetailimage(dto.getDetailimage());
		 return product;
	}



}
