package app.igesa.repository;

import app.igesa.entity.Cookies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Collection;
import java.util.Optional;

public interface CookiesRepository extends JpaRepository<Cookies, Long> ,JpaSpecificationExecutor<Cookies>{
    Optional<Cookies> findFirstByEntrepriseId(Long id);


}
