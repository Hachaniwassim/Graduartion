package app.igesa.repository;
import app.igesa.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

/**
 * @author Tarchoun.Abir
 *
 */
public interface IpostRepository extends JpaRepository<Post,Long> {
    
//List<Post> findByEntrepriseId(Long id);
}
