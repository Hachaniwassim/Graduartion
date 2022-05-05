package app.igesa.dto;
import app.igesa.entity.Entreprise;
import app.igesa.entity.Groupe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.List;


@Data
@Builder
public class EntrepriseDTO {

	private Long id ;
	private String email ;
	private String phone ;
	private String codefiscale ;
	private String fax ;
	private String note ;
	private String companyname ;
	private GroupeDTO groupe;
	protected Date createdDate;
	protected Date lastModifiedDate;
	private Long groupeId;
	@JsonIgnore
	private List<ConfigGeneralDTO> config ;
	

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
				.lastModifiedDate(entreprise.getLastModifiedDate())
				.groupe(GroupeDTO.fromEntity(entreprise.getGroupe()))
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
				entreprise.setGroupe(GroupeDTO.toEntity(dto.getGroupe()));
		        Groupe groupe = new Groupe();
		        groupe.setId(dto.getGroupeId());
		        entreprise.setGroupe(groupe);
				return entreprise;
    }


}