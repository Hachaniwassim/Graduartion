package app.igesa.dto;

import app.igesa.entity.Entreprise;
import app.igesa.entity.Post;
import app.igesa.enumerations.Types;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 *
 * @author Tarchoun Abir
 *
 */

@Data
@Builder
public class PostDTO {

	private Long id ;
	private String title ;
	@Column(columnDefinition="text")
	private String description;
	@Column(columnDefinition="text")
	private String htmlContent ;
	private String slug ;
	protected Date createdDate;
	protected Date lastModifiedDate;
	private Long entrepriseId ;

	public static PostDTO fromEntity(Post post) {
		if (post == null) {
			return null;
		}
		return PostDTO.builder()
				.id(post.getId())
				.title(post.getTitle())
				.description(post.getDescription())
				.slug(post.getSlug())
				.htmlContent(post.getHtmlContent())
				.createdDate(post.getCreatedDate())
				.lastModifiedDate(post.getLastModifiedDate())
				.entrepriseId(post.getEntreprise().getId())
				.build();
	}

	public static Post toEntity(PostDTO dto) {

		if (dto == null) {
			return null;
		}

		Post post = new Post();
		post.setId(dto.getId());
		post.setHtmlContent(dto.getHtmlContent());
		post.setDescription(dto.getDescription());
		post.setSlug(dto.getSlug());
		post.setCreatedDate(post.getCreatedDate());
		post.setLastModifiedDate(post.getLastModifiedDate());
		//===========================> Entreprise ===========================>
		Entreprise entreprise = new Entreprise();
		entreprise.setId(dto.getEntrepriseId());
		post.setEntreprise(entreprise);
		return post;
	}





}
