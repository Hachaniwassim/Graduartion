package app.igesa.repository.pages;
import app.igesa.entity.Page3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;

public interface Page3Repository extends JpaRepository<Page3, Long> ,JpaSpecificationExecutor<Page3>{
    Optional<Page3> findFirstByEntrepriseId(Long id);


}
