package app.igesa.dto;

import java.util.Date;
import java.util.List;

import app.igesa.entity.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import app.igesa.entity.Groupe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Temporal;

import static javax.persistence.TemporalType.TIMESTAMP;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GroupeDTO {
    private Long id ; 
    private String name ;
    private String description ; 
    private boolean active ;
    private boolean confirmed;
    private boolean deleted ;
    protected Date createdDate;
    protected Date lastModifiedDate;
    protected String createdBy;
    private CompanyBusinessDTO companyBusiness;
    
    @JsonIgnore
    private List<EntrepriseDTO>entreprise ;
    @JsonIgnore
   // private List<RoleDTO>role;




    public static GroupeDTO fromEntity(Groupe groupe) {

		return GroupeDTO.builder()
				.id(groupe.getId())
				.name(groupe.getName())
				.active(groupe.isActive())
				.confirmed(groupe.isConfirmed())
				.deleted(groupe.isDeleted())
				.description(groupe.getDescription())
                .lastModifiedDate(groupe.getLastModifiedDate())
                .createdDate(groupe.getLastModifiedDate())
                .createdBy(groupe.getCreatedBy())
                .companyBusiness(CompanyBusinessDTO.fromEntity(groupe.getCompanyBusiness()))
                .build();
				//boucle infini
				/*.entreprise(groupe.getEntreprise() != null? 
						       groupe.getEntreprise().stream().map(EntrepriseDTO::fromEntity).collect(Collectors.toList()):null)*/

    }


    
    public static Groupe toEntity(GroupeDTO dto) {

    	
           Groupe groupe = new Groupe();
           groupe.setId(dto.getId());
           groupe.setName(dto.getName());
           groupe.setActive(dto.isActive());
           groupe.setConfirmed(dto.isConfirmed());
           groupe.setDeleted(dto.isDeleted());
           groupe.setDescription(dto.getDescription());
           groupe.setLastModifiedDate(dto.getLastModifiedDate());
           groupe.setCreatedDate(dto.getCreatedDate());
           groupe.setCreatedDate(dto.getCreatedDate());
           groupe.setCompanyBusiness(CompanyBusinessDTO.toEntity(dto.getCompanyBusiness()));
          /* groupe.setEntreprise(dto.getEntreprise()!= null? dto.getEntreprise().stream().map(EntrepriseDTO::toEntity).collect(Collectors.toList()):null);
		   */
        return groupe;
    }
}
