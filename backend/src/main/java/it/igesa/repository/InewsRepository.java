package it.igesa.repository;
import it.igesa.domaine.Nwes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;


/***
 * @author  Tarchoun Abir
 */
public interface InewsRepository extends JpaRepository<Nwes,Long> , JpaSpecificationExecutor<Nwes> {
    List<Nwes> findByEntrepriseId(Long id);
}
