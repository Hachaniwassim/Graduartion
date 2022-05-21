package app.igesa.dto;
import java.util.Date;
import java.util.List;
import app.igesa.entity.CompanyBusiness;
import app.igesa.enumerations.GroupStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import app.igesa.entity.Groupe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author Tarchoun Abir
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GroupeDTO {
    private Long id ; 
    private String name ;
    private String description ; 
    private GroupStatus groupStatus;
    protected Date createdDate;
    protected Date lastModifiedDate;
    private Long companyId;

    public static GroupeDTO fromEntity(Groupe groupe) {

		return GroupeDTO.builder()
				.id(groupe.getId())
				.name(groupe.getName())
                .groupStatus(groupe.getGroupStatus())
				.description(groupe.getDescription())
                .lastModifiedDate(groupe.getLastModifiedDate())
                .createdDate(groupe.getCreatedDate())
                .companyId(groupe.getCompanyBusiness().getId())
                        .build();

    }


    
    public static Groupe toEntity(GroupeDTO dto) {

    	
           Groupe groupe = new Groupe();
           groupe.setId(dto.getId());
           groupe.setName(dto.getName());
           groupe.setGroupStatus(dto.getGroupStatus());
           groupe.setDescription(dto.getDescription());
           groupe.setLastModifiedDate(dto.getLastModifiedDate());
           groupe.setCreatedDate(dto.getCreatedDate());
           //===========================> company Bussiness ===========================>
           CompanyBusiness companyBusiness = new CompanyBusiness();
           companyBusiness.setId(dto.getCompanyId());
           groupe.setCompanyBusiness(companyBusiness);

        return groupe;
    }
}
