package it.igesa.repository;
import it.igesa.domaine.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;


/**
 * @author Tarchoun.Abir
 */

public interface ItagsRepository extends JpaRepository<Tags,Long> , JpaSpecificationExecutor<Tags> {
   // Optional<Tags> findFirstByEntrepriseId(Long id);
    List<Tags> findByEntrepriseId(Long id_enterprise);
}
