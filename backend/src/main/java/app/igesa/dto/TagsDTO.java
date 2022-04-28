package app.igesa.dto;
import app.igesa.entity.Tags;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class TagsDTO {
	private Long id ;
	private String name ;
	private String description ;
	protected Date createdDate;
	protected Date lastModifiedDate;
	protected String createdBy;
	@JsonIgnore
	private List<PostDTO> pots;
	@JsonIgnore
	private List<ProductDTO> products;


	public static TagsDTO fromEntity(Tags tag) {
		if ( tag == null) {
			return null;
		}
		return TagsDTO.builder()
				.id(tag.getId())
				.name((tag.getName()))
				.description(tag.getDescription())
				//.companyBusiness(CompanyBusinessDTO.fromEntity(entreprise.getCompanyBusiness()))
				.build();
	}

	public static Tags toEntity(TagsDTO dto) {

		if (dto == null) {
			return null;
		}

		Tags tag = new Tags();
		tag.setId(dto.getId());
		tag.setDescription(dto.getDescription());
		tag.setName(dto.getName());
		//entreprise.setCompanyBusiness(CompanyBusinessDTO.toEntity(dto.getCompanyBusiness()));
		return tag;
	}




}
