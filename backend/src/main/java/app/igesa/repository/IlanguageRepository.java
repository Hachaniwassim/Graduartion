package app.igesa.repository;
import app.igesa.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Wassim hachaani
 */
public interface IlanguageRepository extends JpaRepository<Language,Long>, JpaSpecificationExecutor<Language> {


}