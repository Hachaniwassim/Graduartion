package it.igesa.repository.pages;
import it.igesa.domaine.pages.Pages;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IpagesRepository extends JpaRepository<Pages,Long> {
    Optional<Pages> findFirstByEntrepriseId(Long id);
    List<Pages> findByEntrepriseId(Long id);
}
