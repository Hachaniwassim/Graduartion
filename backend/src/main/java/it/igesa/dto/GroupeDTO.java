package it.igesa.dto;
import java.util.Date;
import it.igesa.enumerations.GroupStatus;
import it.igesa.domaine.Groupe;
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
    private String maxOperateur;

    public static GroupeDTO fromEntity(Groupe groupe) {

		return GroupeDTO.builder()
				.id(groupe.getId())
				.name(groupe.getName())
                .groupStatus(groupe.getGroupStatus())
				.description(groupe.getDescription())
                .lastModifiedDate(groupe.getLastModifiedDate())
                .createdDate(groupe.getCreatedDate())
                //nombre operateur
                .maxOperateur(groupe.getMaxOperateur())
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
           //maxOperateur
           groupe.setMaxOperateur(dto.getMaxOperateur());

        return groupe;
    }
}
