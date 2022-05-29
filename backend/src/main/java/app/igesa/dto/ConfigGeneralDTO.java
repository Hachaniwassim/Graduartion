package app.igesa.dto;

import app.igesa.entity.ConfigGenerale;
import app.igesa.entity.Entreprise;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Tarchoun Abir
 */

@Data
@Builder
public class ConfigGeneralDTO {
	
	private Long id ;
	private String title ;
	private String facebook ;
	private String twitter ;
	private String youtube ;
	private String image;
	private String favicon ;
	private String adresse ;
	private String email ;
	private String phone ;
	private String fax ;
	private String tagline ;
	private String copyright ;
	private String newslettertitle;
	private String newslettersubtitle ;
	protected Date createdDate;
	protected Date lastModifiedDate;
	private Long entrepriseId ;

	public static ConfigGeneralDTO fromEntity(ConfigGenerale config) {
		if ( config == null) {
			return null;
		}
		return ConfigGeneralDTO.builder()
				.id(config.getId())
				.title(config.getTitle())
				.facebook(config.getFacebook())
				.adresse(config.getAdresse())
				.copyright(config.getCopyright())
				.email(config.getEmail())
				.favicon(config.getFavicon())
				.fax(config.getFax())
				.image(config.getImage())
				.phone(config.getPhone())
				.youtube(config.getYoutube())
				.twitter(config.getTwitter())
				.newslettertitle(config.getNewslettertitle())
				.newslettersubtitle(config.getNewslettersubtitle())
				//.robotsTags(config.getRobotsTags())
				.createdDate(config.getCreatedDate())
				.lastModifiedDate(config.getLastModifiedDate())
				.entrepriseId(config.getEntreprise().getId())
				.build();
	}

	public static ConfigGenerale toEntity(ConfigGeneralDTO dto) {

		if (dto == null) {
			return null;
		}

		ConfigGenerale config= new ConfigGenerale();
			    config.setId(config.getId());
				config.setTitle(dto.getTitle());
				config.setFacebook(dto.getFacebook());
				config.setAdresse(dto.getAdresse());
				config.setCopyright(dto.getCopyright());
				config.setEmail(dto.getEmail());
				config.setFavicon(dto.getFavicon());
				config.setFax(dto.getFax());
				config.setImage(dto.getImage());
				config.setPhone(dto.getPhone());
				config.setYoutube(dto.getYoutube());
				config.setTwitter(dto.getTwitter());
				config.setNewslettersubtitle(dto.getNewslettersubtitle());
				config.setNewslettertitle(dto.getNewslettertitle());
				config.setLastModifiedDate(dto.getLastModifiedDate());
				config.setCreatedDate(config.getCreatedDate());
				//===============================> enterprise
		        Entreprise entreprise = new Entreprise();
		        entreprise.setId(dto.getEntrepriseId());
		        config.setEntreprise(entreprise);
				return config;

	}


}
