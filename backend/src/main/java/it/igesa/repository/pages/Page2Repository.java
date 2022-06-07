package it.igesa.repository.pages;
import it.igesa.domaine.pages.Page2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;
import java.util.Optional;

public interface Page2Repository extends JpaRepository<Page2, Long> ,JpaSpecificationExecutor<Page2>{
    Optional<Page2> findFirstByEntrepriseId(Long id);
    List<Page2> findByEntrepriseId(Long id);


}
