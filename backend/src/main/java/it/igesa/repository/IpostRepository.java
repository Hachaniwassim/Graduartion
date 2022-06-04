package it.igesa.repository;
import it.igesa.domaine.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Tarchoun.Abir
 *
 */
public interface IpostRepository extends JpaRepository<Post,Long> {
    List<Post> findByEntrepriseId(Long id);
}
