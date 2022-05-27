package app.igesa.repository;
import app.igesa.entity.Category;
import app.igesa.entity.Cookies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author  Tarchoun.Abir
 *
 */
public interface IcategoryRepository extends JpaRepository<Category,Long>, JpaSpecificationExecutor<Category>{


        Optional<Category> findFirstByEntrepriseId(Long id);
        List<Category> findByEntrepriseId(Long id);
}


