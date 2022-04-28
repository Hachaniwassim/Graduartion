package app.igesa.metiers;
import java.util.Collection;
import java.util.List;
import app.igesa.dto.CompanyBusinessDTO;
import app.igesa.entity.CompanyBusiness;

public interface IcompanyBusiness {

	public CompanyBusinessDTO save(CompanyBusinessDTO c);
	public Collection<CompanyBusinessDTO> view();
	public CompanyBusinessDTO findById(Long id);
	public void delete(Long id);
	public List<CompanyBusinessDTO> FindCompanyBusinessByDescription(String description );
}
