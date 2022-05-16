package app.igesa.repository;
import app.igesa.entity.Page2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Collection;

public interface Page2Repository extends JpaRepository<Page2, Long> ,JpaSpecificationExecutor<Page2>{
    Collection<Page2> findFirstByEntreprise(Long entrepriseId);


}
