package app.igesa.dto;

import app.igesa.entity.Post;
import app.igesa.enumerations.Types;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PostDTO {
	
	private Long id ;
	private String title ;
	private String description;
	private String slug ;
	private String subtitle ;
	private String image ;
	private String tagline ;
	private String content ;
	private PageDTO page ;
	private TagsDTO tags;
	protected Date createdDate;
	protected Date lastModifiedDate;
	protected String createdBy;
	private Enum<Types> types;

	public static PostDTO fromEntity(Post post) {
		if (post == null) {
			return null;
		}
		return PostDTO.builder()
				.id(post.getId())
				.title(post.getTitle())
				.description(post.getDescription())
				.slug(post.getSlug())
				.subtitle(post.getSubtitle())
				.image(post.getImage())
				.content(post.getContent())
				.page(PageDTO.fromEntity(post.getPage()))
				.tags(TagsDTO.fromEntity(post.getTags()))
				.types(post.getType())
				.build();
	}

	public static Post toEntity(PostDTO dto) {

		if (dto == null) {
			return null;
		}

		Post post = new Post();
		post.setId(dto.getId());
		post.setContent(dto.getContent());
		post.setDescription(dto.getDescription());
		post.setSubtitle(dto.getSubtitle());
		post.setTagline(dto.getTagline());
		post.setImage(dto.getImage());
		post.setType(dto.getTypes());
		post.setSlug(dto.getSlug());
		post.setCreatedDate(post.getCreatedDate());
		post.setCreatedBy(post.getCreatedBy());
		post.setLastModifiedDate(post.getLastModifiedDate());
		post.setTags(TagsDTO.toEntity(dto.getTags()));
		post.setPage(PageDTO.toEntity(dto.getPage()));
		return post;
	}





}
