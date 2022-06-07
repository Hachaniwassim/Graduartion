package it.igesa.repository.pages;
import it.igesa.domaine.pages.Page1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;
import java.util.Optional;

public interface Page1Repository extends JpaRepository<Page1, Long> ,JpaSpecificationExecutor<Page1>{
    Optional<Page1> findFirstByEntrepriseId(Long id);
    List<Page1> findByEntrepriseId(Long id);

}
