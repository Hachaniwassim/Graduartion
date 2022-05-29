package app.igesa.repository;
import app.igesa.entity.Liens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

/**
 * @author Tarchoun Abir
 *
 */



public interface IliensRepository extends JpaRepository<Liens,Long>, JpaSpecificationExecutor<Liens>  {


    List<Liens> findByEntrepriseId(Long id);
}
