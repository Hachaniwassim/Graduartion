package app.igesa.dto;
import app.igesa.entity.Entreprise;
import app.igesa.entity.FormEntity;
import app.igesa.enumerations.ContactStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Tarchoun Abir
 */

@Data
@Builder
public class FormDTO {
	private Long id ;
	private String name ; 
	private String companyname;
	private String mobile ;
	private String fax ;
	private String email ;
	private String adresse ;
	private String nationality ;
	private String referent ;
	protected Date createdDate;
	protected Date lastModifiedDate;
	protected String createdBy;
	private Long entrepriseId;
	private EntrepriseDTO entreprise;
	private ContactStatus contactStatus;
	//private Product softwareused ;
	private boolean status ;



	public static FormDTO fromEntity(FormEntity contact) {
		if ( contact == null) {
			return null;
		}
		return FormDTO.builder()
				.id(contact.getId())
				.email(contact.getEmail())
				.fax(contact.getFax())
				.referent(contact.getReferent())
				.adresse(contact.getAdresse())
				.companyname(contact.getCompanyname())
				.nationality(contact.getNationality())
				.name(contact.getName())
				.contactStatus(contact.getContactstatus())
				.createdDate(contact.getCreatedDate())
				.lastModifiedDate(contact.getLastModifiedDate())
				.mobile(contact.getMobile())
				.entreprise(EntrepriseDTO.fromEntity(contact.getEntreprise()))
				.build();
	}

	public static FormEntity toEntity(FormDTO dto) {

		if (dto == null) {
			return null;
		}

		FormEntity contact = new FormEntity();
		contact.setId(dto.getId());
		contact.setCompanyname(dto.getCompanyname());
		contact.setEmail(dto.getEmail());
		contact.setFax(dto.getFax());
		contact.setMobile(dto.getMobile());
		contact.setNationality(dto.getNationality());
		contact.setReferent(dto.getReferent());
		contact.setAdresse(dto.getAdresse());
		contact.setLastModifiedDate(dto.getLastModifiedDate());
		contact.setCreatedDate(dto.getCreatedDate());
		contact.setContactstatus(dto.getContactStatus());
		contact.setName(dto.getName());
		Entreprise entreprise = new Entreprise();
		entreprise.setId(dto.getEntrepriseId());
		contact.setEntreprise(entreprise);
		return contact;
	}


}
