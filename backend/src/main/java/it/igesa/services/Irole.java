package it.igesa.services;
import java.util.List;
import it.igesa.dto.RoleDTO;
/**
 *
 * @author Tarchoun Abir
 *
 */

public interface Irole {
    public RoleDTO save(RoleDTO r);
    List<RoleDTO> view();
    public RoleDTO findById(Long id);
    public void delete(Long id);
    public void updateRole(Long role_id,Long account_id);


}
