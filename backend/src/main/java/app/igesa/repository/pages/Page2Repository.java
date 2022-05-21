package app.igesa.repository.pages;
import app.igesa.entity.Page2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;

public interface Page2Repository extends JpaRepository<Page2, Long> ,JpaSpecificationExecutor<Page2>{
    Optional<Page2> findFirstByEntrepriseId(Long id);


}
