package app.igesa.dto;

import java.util.Date;
import java.util.List;
import app.igesa.entity.CompanyBusiness;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyBusinessDTO {

    private Long id ;
	private String description ; 
	private String domainename ;
	protected Date createdDate;
	protected Date lastModifiedDate;
	@JsonIgnore
	private List<GroupeDTO>groupe ;


	public static CompanyBusinessDTO fromEntity(CompanyBusiness company) {

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

		/*if (dto == null) {
			return null;
		}*/

		CompanyBusiness company = new CompanyBusiness();
		company.setId(dto.getId());
		company.setDescription(dto.getDescription());
		company.setDomainename(dto.getDomainename());
		company.setCreatedDate(dto.getCreatedDate());
		company.setLastModifiedDate(dto.getLastModifiedDate());
		return company;
	}

}
