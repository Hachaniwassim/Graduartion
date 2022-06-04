package it.igesa.repository;
import it.igesa.domaine.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Wassim hachaani
 */
public interface IlanguageRepository extends JpaRepository<Language,Long>, JpaSpecificationExecutor<Language> {

   List<Language> findByEntrepriseId(Long id);
}