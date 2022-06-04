package it.igesa.repository.siteinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import it.igesa.domaine.siteinfo.Plateforme;

import java.util.List;

public interface IplateformeRepository extends JpaRepository<Plateforme, Long>{
   List<Plateforme> findByEntrepriseId(Long enterpriseId);



}
