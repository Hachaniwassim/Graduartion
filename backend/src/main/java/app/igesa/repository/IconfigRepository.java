package app.igesa.repository;

import app.igesa.entity.Category;
import app.igesa.entity.ConfigGenerale;
import app.igesa.entity.Cookies;
import app.igesa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * @author Tarchoun.Abir
 *
 */
public interface IconfigRepository extends JpaRepository<ConfigGenerale,Long>, JpaSpecificationExecutor<ConfigGenerale> {
    Optional<ConfigGenerale> findFirstByEntrepriseId(Long id);
    List<ConfigGenerale> findByEntrepriseId(Long id);
}
