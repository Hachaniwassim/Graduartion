package app.igesa.repository;
import app.igesa.entity.Page1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Collection;

public interface Page1Repository extends JpaRepository<Page1, Long> ,JpaSpecificationExecutor<Page1>{
    Collection<Page1> findFirstByEntreprise(Long entrepriseId);


}
