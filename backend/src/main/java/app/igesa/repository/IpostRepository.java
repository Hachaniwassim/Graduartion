package app.igesa.repository;
import app.igesa.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


/**
 * @author Tarchoun.Abir
 *
 */
public interface IpostRepository extends JpaRepository<Post,Long> {
    //Optional<Post> findFirstByEntrepriseId(Long id);
}
