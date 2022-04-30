package app.igesa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.igesa.entity.Plateforme;

public interface IplateformeRepository extends JpaRepository<Plateforme, Long>{
	  // List<PlateformeDTO> findByPublished(boolean published);


}
