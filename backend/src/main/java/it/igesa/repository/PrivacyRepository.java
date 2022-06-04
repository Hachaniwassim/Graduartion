package it.igesa.repository;
import it.igesa.domaine.Privacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;
import java.util.Optional;

/***
 * @author Tarchoun.Abir
 */
public interface PrivacyRepository extends JpaRepository<Privacy, Long>, JpaSpecificationExecutor<Privacy> {
    Optional<Privacy> findFirstByEntrepriseId(Long id);
    List<Privacy> findByEntrepriseId(Long  id_entreprise);
   // Privacy findPrivacyByEntrepriseId(Long id_entreprise);
}
