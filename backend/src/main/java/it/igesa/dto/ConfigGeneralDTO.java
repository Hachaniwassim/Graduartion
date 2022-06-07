package it.igesa.dto;
import it.igesa.domaine.ConfigGenerale;
import it.igesa.domaine.Entreprise;
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
	private String facebook ;
	private String twitter ;
	private String youtube ;
	private String adresse ;
	private String email ;
	private String phone ;
	private String fax ;
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
				.facebook(config.getFacebook())
				.adresse(config.getAdresse())
				.copyright(config.getCopyright())
				.email(config.getEmail())
				.fax(config.getFax())
				.phone(config.getPhone())
				.youtube(config.getYoutube())
				.twitter(config.getTwitter())
				.newslettertitle(config.getNewslettertitle())
				.newslettersubtitle(config.getNewslettersubtitle())
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
				config.setFacebook(dto.getFacebook());
				config.setAdresse(dto.getAdresse());
				config.setCopyright(dto.getCopyright());
				config.setEmail(dto.getEmail());
				config.setFax(dto.getFax());
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
