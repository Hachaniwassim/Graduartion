package app.igesa.dto;

import app.igesa.entity.Tags;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Tarchoun Abir
 */

@Builder
@Data
public class TagsDTO {
	private Long id ;
	private String description ;
	protected Date createdDate;
	protected Date lastModifiedDate;
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
				.description(tag.getDescription())
				.lastModifiedDate(tag.getLastModifiedDate())
				.createdDate(tag.getCreatedDate())
				.build();
	}

	public static Tags toEntity(TagsDTO dto) {

		if (dto == null) {
			return null;
		}

		Tags tag = new Tags();
		tag.setId(dto.getId());
		tag.setDescription(dto.getDescription());
		tag.setLastModifiedDate(dto.getLastModifiedDate());
		tag.setCreatedDate(dto.getCreatedDate());
		tag.setCreatedDate(dto.getCreatedDate());

		return tag;
	}




}
