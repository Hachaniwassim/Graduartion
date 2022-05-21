package app.igesa.dto;

/*
 *
 *
 * @author Tarchoun Abir
 *
 *
 */
import app.igesa.entity.Entreprise;
import app.igesa.entity.Groupe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Column;
import java.util.Date;
import java.util.List;

/**
 *  @author Tarchoun Abir
 */

@Data
@Builder
public class EntrepriseDTO {

	private Long id ;
	private String email ;
	private String phone ;
	private String codefiscale  ;
	private String fax ;
	private String note ;
	@Column(unique = true, nullable = false)
	private String companyname ;
	protected Date createdDate;
	protected Date lastModifiedDate;
	private Long groupeId;

    public static EntrepriseDTO fromEntity(Entreprise entreprise) {

		return EntrepriseDTO.builder()
				.id(entreprise.getId())
				.codefiscale(entreprise.getCodefiscale())
				.companyname(entreprise.getCompanyname())
				.email(entreprise.getEmail())
				.fax(entreprise.getFax())
				.note(entreprise.getNote())
				.phone(entreprise.getPhone())
				.createdDate(entreprise.getCreatedDate())
				.groupeId(entreprise.getGroupe().getId())
				.lastModifiedDate(entreprise.getLastModifiedDate())
				.build();
    }
    
    public static Entreprise toEntity(EntrepriseDTO dto) {

    	        Entreprise entreprise = new Entreprise();
	            entreprise.setId(dto.getId());
				entreprise.setCodefiscale(dto.getCodefiscale());
				entreprise.setCompanyname(dto.getCompanyname());
				entreprise.setEmail(dto.getEmail());
			    entreprise.setFax(dto.getFax());
			    entreprise.setNote(dto.getNote());
				entreprise.setPhone(dto.getPhone());
				entreprise.setCreatedDate(dto.getCreatedDate());
				entreprise.setLastModifiedDate(dto.getLastModifiedDate());
		        //===========================> Groupe ===========================>
		        Groupe groupe = new Groupe();
		        groupe.setId(dto.getGroupeId());
		        entreprise.setGroupe(groupe);
				return entreprise;
    }


}