package it.igesa.dto;
import it.igesa.domaine.Role;
import it.igesa.enumerations.ERole;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author Tarchoun Abir
 *
 */
@Data
@Builder
public class RoleDTO {
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public static RoleDTO fromEntity(Role roledto) {

        return RoleDTO.builder()
                .id(roledto.getId())
                .name(roledto.getName())
                .build();
    }

    public static Role toEntity(RoleDTO dto) {

        Role role = new Role() ;
        role.setId(dto.getId());
        role.setName(dto.getName());
        return role;
    }
}
