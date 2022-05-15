package app.igesa.repository;

import app.igesa.dto.CompanyBusinessDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import app.igesa.entity.CompanyBusiness;

import java.util.List;
import java.util.Optional;


public interface IcomapnybusRepository extends JpaRepository<CompanyBusiness,Long> {

	/*Page<CompanyBusiness> findCompanyBusinessByDescription(@Param("x")String description,Pageable p);*/

	@Query("select po from CompanyBusiness po where po.description like :x")
	List<CompanyBusiness> findCompanyBusinessesByDescription(@Param("x")String description);

}
