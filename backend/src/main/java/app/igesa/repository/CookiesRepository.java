package app.igesa.repository;

import app.igesa.entity.Cookies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Collection;

public interface CookiesRepository extends JpaRepository<Cookies, Long> ,JpaSpecificationExecutor<Cookies>{
    Collection<Cookies> findFirstByEntreprise(Long entrepriseId);


}
