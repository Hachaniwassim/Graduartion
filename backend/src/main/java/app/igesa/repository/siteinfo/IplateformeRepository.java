package app.igesa.repository.siteinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import app.igesa.entity.siteinfo.Plateforme;

import java.util.List;
import java.util.Optional;

public interface IplateformeRepository extends JpaRepository<Plateforme, Long>{
   List<Plateforme> findByEntrepriseId(Long enterpriseId);



}
