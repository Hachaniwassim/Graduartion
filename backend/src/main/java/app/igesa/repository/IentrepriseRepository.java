package app.igesa.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.igesa.entity.Entreprise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
/**
 * @author Tarchoun.Abir
 *
 */
public interface IentrepriseRepository  extends JpaRepository<Entreprise,Long>{
    @Query("select po from Entreprise po where po.companyname like :x")
    List<Entreprise> findEntrepriseByCompanyname(@Param("x")String companyname);

    @Query(value="SELECT * FROM Entreprise f WHERE f.groupeId= :groupeId" ,nativeQuery = true)
    List<Entreprise> findEntrepriseByGroupe(@Param("groupeId") Long id);
}