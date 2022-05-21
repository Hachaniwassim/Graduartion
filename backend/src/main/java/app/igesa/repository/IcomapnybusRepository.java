package app.igesa.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import app.igesa.entity.CompanyBusiness;
import java.util.List;

/**
 * @author Wassim Hachaani
 */

public interface IcomapnybusRepository extends JpaRepository<CompanyBusiness,Long> {

	/*Page<CompanyBusiness> findCompanyBusinessByDescription(@Param("x")String description,Pageable p);*/

	@Query("select po from CompanyBusiness po where po.description like :x")
	List<CompanyBusiness> findCompanyBusinessesByDescription(@Param("x")String description);

}
