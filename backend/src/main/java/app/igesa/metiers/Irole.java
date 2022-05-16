package app.igesa.metiers;

import app.igesa.entity.Role;
import java.util.List;
import java.util.Optional;


public interface Irole {
    public Role save(Role r);
    public Optional<Role> findById(Long id);

    List<Role> findAll();

    public void delete(Long id);
    public Role update(Long id, Role r);

}
