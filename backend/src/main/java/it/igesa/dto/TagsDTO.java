package it.igesa.dto;

import it.igesa.domaine.Entreprise;
import it.igesa.domaine.Tags;
import lombok.Builder;
import lombok.Data;
import java.util.Date;


/**
 *
 * @author Tarchoun Abir
 */

@Builder
@Data
public class TagsDTO {
	private  Long id ;
	private String description ;
	protected Date createdDate;
	protected Date lastModifiedDate;
	private   Long entrepriseId;


	public static TagsDTO fromEntity(Tags tag) {

		return TagsDTO.builder()
				.id(tag.getId())
				.description(tag.getDescription())
				.lastModifiedDate(tag.getLastModifiedDate())
				.createdDate(tag.getCreatedDate())
				.entrepriseId(tag.getEntreprise().getId())
				.build();
	}

	public static Tags toEntity(TagsDTO dto) {

		Tags tag = new Tags();
		tag.setId(dto.getId());
		tag.setDescription(dto.getDescription());
		tag.setLastModifiedDate(dto.getLastModifiedDate());
		tag.setCreatedDate(dto.getCreatedDate());
		//======================================>Entreprise<=============================
		Entreprise entreprise = new Entreprise();
		entreprise.setId(dto.getEntrepriseId());
		tag.setEntreprise(entreprise);
		return tag;
	}




}
