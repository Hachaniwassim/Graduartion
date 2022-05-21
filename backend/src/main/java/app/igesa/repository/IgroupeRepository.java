package app.igesa.repository;
import app.igesa.enumerations.GroupStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import app.igesa.entity.Groupe;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

/***
 * @author Tarchoun.Abir
 *
 */

public interface IgroupeRepository extends JpaRepository<Groupe, Long> , JpaSpecificationExecutor<Groupe> {
    List<Groupe> findByGroupStatus(GroupStatus status);
    List<Groupe> findAllById(Long id);
}