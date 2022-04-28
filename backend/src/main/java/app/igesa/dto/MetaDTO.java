package app.igesa.dto;

import app.igesa.entity.Meta;
import app.igesa.enumerations.PagesTypes;
import app.igesa.enumerations.RobotsTags;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class MetaDTO {
	
	private Long id ;
	private String metatitle ;
	private String metadescription ;
	private String  urlkey;
	private String metakey ;
	protected Date createdDate;
	protected Date lastModifiedDate;
	protected String createdBy;
	private PagesTypes pagesTypes;
	private ProductDTO product;
	private RobotsTags robotsTags;


	public static MetaDTO fromEntity(Meta meta ) {

		return MetaDTO.builder()
				.id(meta.getId())
				.metakey(meta.getMetakey())
				.metatitle(meta.getMetatitle())
				.metadescription(meta.getMetadescription())
				.urlkey(meta.getUrlkey())
				.pagesTypes(meta.getPagesTypes())
				.robotsTags(meta.getRobotsTags())
				.product(ProductDTO.fromEntity(meta.getProduct()))
				.build();
	}


	public static Meta toEntity(MetaDTO dto) {
		if (dto==null) {
			return null; }
		    Meta meta = new Meta();
		    meta.setId(dto.getId());
			meta.setMetakey(dto.getMetakey());
			meta.setMetatitle(dto.getMetatitle());
			meta.setMetadescription(dto.getMetadescription());
			meta.setUrlkey(dto.getUrlkey());
			meta.setRobotsTags(dto.getRobotsTags());
			meta.setPagesTypes(dto.getPagesTypes());
			return meta;
	}
}
