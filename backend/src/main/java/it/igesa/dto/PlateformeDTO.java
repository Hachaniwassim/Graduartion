package it.igesa.dto;
import it.igesa.domaine.Entreprise;
import it.igesa.domaine.siteinfo.Plateforme;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

/**
 * @author Tarchoun Abir
 *
 */

@Data
@Builder
public class PlateformeDTO {
	
	private Long id ; 
	private String email ; 
	private String phone ;
	private String adresse ;
	private Boolean published;
	protected Date createdDate;
	protected Date lastModifiedDate;
	protected String createdBy;
	private  Long entrepriseId;
	public static PlateformeDTO fromEntity(Plateforme plateforme) {
    	if ( plateforme == null) {
    		return null;
    	}
		return PlateformeDTO.builder()
				.id(plateforme.getId())
				.email(plateforme.getEmail())
				.phone(plateforme.getPhone())
				.adresse(plateforme.getAdresse())
				.published(plateforme.getPublished())
				.lastModifiedDate(plateforme.getLastModifiedDate())
				.createdDate(plateforme.getCreatedDate())
				.entrepriseId(plateforme.getEntreprise().getId())
				.build();
	}

	public static Plateforme toEntity(PlateformeDTO dto) {

    	if (dto == null) {
    		return null;
    	}

		Plateforme plateforme = new Plateforme();
		plateforme.setId(dto.getId());
		plateforme.setAdresse(dto.getAdresse());
		plateforme.setEmail(dto.getEmail());
		plateforme.setPhone(dto.getPhone());
		plateforme.setPublished(dto.getPublished());
		plateforme.setLastModifiedDate(dto.getLastModifiedDate());
		plateforme.setCreatedDate(dto.getCreatedDate());
		//===========================> Entreprise ===========================>
		Entreprise entreprise = new Entreprise();
		entreprise.setId(dto.getEntrepriseId());
		plateforme.setEntreprise(entreprise);
		return plateforme;
	}




}
