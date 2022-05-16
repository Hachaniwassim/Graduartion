package app.igesa.repository;

import app.igesa.entity.ConfigGenerale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IconfigRepository extends JpaRepository<ConfigGenerale,Long>{

}
