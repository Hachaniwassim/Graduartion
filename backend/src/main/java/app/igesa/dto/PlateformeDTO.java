package app.igesa.dto;

import app.igesa.entity.Entreprise;
import app.igesa.entity.Plateforme;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PlateformeDTO {
	
	private Long id ; 
	private String email ; 
	private String phone ;
	private String adresse ;
	private Boolean published;
	private EntrepriseDTO entreprise;
	protected Date createdDate;
	protected Date lastModifiedDate;
	protected String createdBy;
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
				.entreprise(EntrepriseDTO.fromEntity(plateforme.getEntreprise()))
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
		plateforme.setEntreprise(EntrepriseDTO.toEntity(dto.getEntreprise()));
		return plateforme;
	}




}
