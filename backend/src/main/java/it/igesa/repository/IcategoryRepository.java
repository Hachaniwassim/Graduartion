package it.igesa.repository;
import it.igesa.domaine.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 *
 * @author  Tarchoun.Abir
 *
 */
public interface IcategoryRepository extends JpaRepository<Category,Long>, JpaSpecificationExecutor<Category>{


        //Optional<Category> findFirstByEntrepriseId(Long id);
        List<Category> findByEnterpriseId(Long id);
}


