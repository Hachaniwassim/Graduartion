package app.igesa.repository.pages;
import app.igesa.entity.Cookies;
import app.igesa.entity.pages.Page1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Collection;
import java.util.Optional;

public interface Page1Repository extends JpaRepository<Page1, Long> ,JpaSpecificationExecutor<Page1>{
    Optional<Page1> findFirstByEntrepriseId(Long id);


}
