package it.igesa.repository;
import it.igesa.domaine.Cookies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * @author Wassim Hachaani
 */
public interface CookiesRepository extends JpaRepository<Cookies, Long> ,JpaSpecificationExecutor<Cookies>{
    Optional<Cookies> findFirstByEntrepriseId(Long id);
    List<Cookies> findByEntrepriseId(Long id);


}
