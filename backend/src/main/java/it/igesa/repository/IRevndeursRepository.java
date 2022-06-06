package it.igesa.repository;
import it.igesa.domaine.PostRevendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IRevndeursRepository extends JpaRepository<PostRevendeur,Long> {
    Optional<PostRevendeur> findFirstByEntrepriseId(Long id);
    List<PostRevendeur> findByEntrepriseId(Long id);
}
