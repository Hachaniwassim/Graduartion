package it.igesa.repository;
import it.igesa.domaine.Logo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

/**
 *
 * @author Tarchoun Abir
 *
 */

public interface IbrandRepository extends JpaRepository<Logo,Long>, JpaSpecificationExecutor<Logo> {


    List<Logo> findByEntrepriseId(Long id);

}
