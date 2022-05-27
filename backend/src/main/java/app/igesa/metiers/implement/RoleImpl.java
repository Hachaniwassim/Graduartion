package app.igesa.metiers.implement;
import java.util.List;
import java.util.stream.Collectors;
import app.igesa.dto.RoleDTO;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.metiers.Irole;
import app.igesa.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.igesa.entity.Role;

/**
 *
 * @author Wassim Hachani
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Service
public class RoleImpl  implements Irole {


    @Autowired
    private RoleRepository roleRepository;

    private static final Logger log = LoggerFactory.getLogger(RoleImpl.class);



    @Override
    public RoleDTO save(RoleDTO r) {
        Role saved =roleRepository.save(RoleDTO.toEntity(r));
        return RoleDTO.fromEntity(saved);
    }



    @Override
    public List<RoleDTO> view() {
        return roleRepository.findAll().stream()
                .map(RoleDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {
        if ( id == null) {
            log.error(" Tags Id is NULL .. !!");
            return null ;
        }

        return roleRepository.findById(id).map(RoleDTO::fromEntity).orElseThrow(()->
                new ResourceNotFoundException(" No Tags  with  Id = :: " +id+ " was founded {} ..!",
                        ErrorCode.TAGS_NOT_FOUND));
    }


    @Override
    public void delete(Long id) {
        if ( id == null) {
            log.error(" Tags ID IS NULL ");
            return;
        }
        roleRepository.deleteById(id);

    }
    @Override
    public void updateRole(Long role_id,Long account_id){
        roleRepository.updateRoleQuerry(role_id,account_id);

    }


}


