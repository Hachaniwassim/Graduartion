package app.igesa.dto;
import app.igesa.entity.CompanyBusiness;
import app.igesa.entity.Entreprise;
import app.igesa.entity.Groupe;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import java.util.Date;

/**
 *  @author Tarchoun Abir
 */

@Data
@Builder
public class EntrepriseDTO {

	private Long id ;
	@Email
	private String email ;
	private String phone ;
	private String codefiscale  ;
	private String fax ;
	@Column(columnDefinition="text")
	private String note ;
	@Column(unique = true, nullable = false)
	private String companyname ;
	protected Date createdDate;
	protected Date lastModifiedDate;
	private Long groupeId;
	private String websiteUrl;
	private String adresse;
	private String city ;
	private String street;
	private String refrente;
	private String Vatnumber;
	private Long CompanyBusinessId ;

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
				.websiteUrl(entreprise.getWebsiteUrl())
				.adresse(entreprise.getAdresse())
				.refrente(entreprise.getRefrente())
				.street(entreprise.getStreet())
				.Vatnumber(entreprise.getVatnumber())
				.city(entreprise.getCity())
				.groupeId(entreprise.getGroupe().getId())
				.CompanyBusinessId(entreprise.getCompanyBusiness().getId())
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
		entreprise.setAdresse(dto.getAdresse());
		entreprise.setRefrente(dto.getRefrente());
		entreprise.setStreet(dto.getStreet());
		entreprise.setWebsiteUrl(dto.getWebsiteUrl());
		entreprise.setCity(dto.getCity());

		//===========================> Groupe ===========================>
		Groupe groupe = new Groupe();
		groupe.setId(dto.getGroupeId());
		entreprise.setGroupe(groupe);
		//===========================> company Bussiness ===========================>
		CompanyBusiness companyBusiness = new CompanyBusiness();
		companyBusiness.setId(dto.getCompanyBusinessId());
		entreprise.setCompanyBusiness(companyBusiness);
		return  entreprise;
	}
}