package it.igesa.dto;

import java.util.Date;

import it.igesa.domaine.CompanyBusiness;
import lombok.Builder;
import lombok.Data;

/**
 * @author Tarchoun Abir
 */

@Data
@Builder
public class CompanyBusinessDTO {

    private Long id ;
	private String description ; 
	private String domainename ;
	protected Date createdDate;
	protected Date lastModifiedDate;


	public static CompanyBusinessDTO fromEntity(CompanyBusiness company) {

		if (company == null) {
			return null;
		}

		return CompanyBusinessDTO.builder()
				.id(company.getId())
				.description(company.getDescription())
				.domainename(company.getDomainename())
				//.createdBy(company.getCreatedBy())
				.lastModifiedDate(company.getLastModifiedDate())
				.createdDate(company.getCreatedDate())
				.build();
	}

	public static CompanyBusiness toEntity(CompanyBusinessDTO dto) {

		if (dto == null) {
			return null;
		}

		CompanyBusiness company = new CompanyBusiness();
		company.setId(dto.getId());
		company.setDescription(dto.getDescription());
		company.setDomainename(dto.getDomainename());
		company.setCreatedDate(dto.getCreatedDate());
		company.setLastModifiedDate(dto.getLastModifiedDate());
		return company;
	}

}
