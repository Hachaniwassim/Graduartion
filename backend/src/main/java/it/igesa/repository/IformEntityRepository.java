package it.igesa.repository;
import it.igesa.domaine.FormEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/**
 * @author Wassim Hachaani
 */
public interface IformEntityRepository  extends JpaRepository<FormEntity,Long> {
    Optional<FormEntity> findFirstByEntrepriseId(Long id);
    List<FormEntity> findByEntrepriseId(Long id);

}
