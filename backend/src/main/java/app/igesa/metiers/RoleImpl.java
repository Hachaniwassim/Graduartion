package app.igesa.metiers;

import java.util.List;
import java.util.Optional;
import app.igesa.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.igesa.entity.Role;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class RoleImpl  implements Irole{


    @Autowired
    private RoleRepository roleRepository;

    private static final Logger log = LoggerFactory.getLogger(RoleImpl.class);

@Override
    public Role save(Role r) {

    log.debug("http post :: ");
    return roleRepository.save(r);
      }
    @Override
    public Optional<Role> findById(Long id) {
       return roleRepository.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    public Role update(Long id, Role r) {
       Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role introuvable avec id : : " +id));
        role.setName(r.getName());
       return roleRepository.save(role);


    }
}


