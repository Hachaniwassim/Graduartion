package app.igesa.repository.pages;
import app.igesa.entity.pages.Pages;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IpagesRepository extends JpaRepository<Pages,Long> {
    Optional<Pages> findFirstByEntrepriseId(Long id);
}
