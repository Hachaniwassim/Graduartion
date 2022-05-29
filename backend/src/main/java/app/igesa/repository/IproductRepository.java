package app.igesa.repository;
import app.igesa.entity.Category;
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
public interface IproductRepository extends JpaRepository<Product,Long> , JpaSpecificationExecutor<Product> {
    Optional<Product> findFirstByEntrepriseId(Long id);
    List<Product> findByEntrepriseId(Long id);
}
   // List<Category> findByEnterpriseId(Long id);
