package app.igesa.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import app.igesa.entity.Entreprise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IentrepriseRepository  extends JpaRepository<Entreprise,Long>{
    @Query("select po from Entreprise po where po.companyname like :x")
    List<Entreprise> findEntrepriseByCompanyname(@Param("x")String companyname);

    @Query(value="SELECT f FROM Entreprise f WHERE f.groupe_id= :idGroupe" ,nativeQuery = true)
    List<Entreprise> findEntrepriseByGroupe(@Param("idGroupe") Long id);
}