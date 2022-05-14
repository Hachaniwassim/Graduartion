package app.igesa.repository;
import app.igesa.entity.Page3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Collection;

public interface Page3Repository extends JpaRepository<Page3, Long> ,JpaSpecificationExecutor<Page3>{
    Collection<Page3> findFirstByEntreprise(Long entrepriseId);


}
