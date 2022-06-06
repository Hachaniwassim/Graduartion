package it.igesa.dto;
import it.igesa.domaine.Entreprise;
import it.igesa.domaine.FormEntity;
import it.igesa.domaine.Product;
import it.igesa.enumerations.ContactStatus;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;


/**
 * @author Tarchoun Abir
 */


@Data
@Builder
public class FormDTO {
	private Long id ;
	private String companyname;
	private String mobile ;
	private String fax ;
	private String email ;
	private String adresse ;
	private String nationality ;
	private String referent ;
	protected Date createdDate;
	protected Date lastModifiedDate;
	private Long entrepriseId;
	private Long productId;
	@Column(columnDefinition="text")
	private String message ;
	private ContactStatus contactStatus;



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
				.createdDate(contact.getCreatedDate())
				.lastModifiedDate(contact.getLastModifiedDate())
				.mobile(contact.getMobile())
				.entrepriseId(contact.getEntreprise().getId())
				.contactStatus(contact.getContactstatus())
				.productId(contact.getProduct().getId())
				.message(contact.getMessage())
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
		contact.setMessage(dto.getMessage());
		//=======================> entreprise
		Entreprise entreprise = new Entreprise();
		entreprise.setId(dto.getEntrepriseId());
		contact.setEntreprise(entreprise);
		//=======================> contact
		Product product = new Product();
		product.setId(dto.getProductId());
		contact.setProduct(product);
		return contact;
	}


}
