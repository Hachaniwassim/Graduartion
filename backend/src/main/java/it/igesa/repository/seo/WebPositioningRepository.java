package it.igesa.repository.seo;
import it.igesa.domaine.seo.Seo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/***
 *
 * @author Tarchoun Abir
 *
 */
public interface WebPositioningRepository extends JpaRepository<Seo,Long> {
    List<Seo> findByEntrepriseId(Long id);
}
