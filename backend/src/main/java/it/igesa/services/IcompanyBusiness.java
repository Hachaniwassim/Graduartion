package it.igesa.services;
import java.util.Collection;
import java.util.List;
import it.igesa.dto.CompanyBusinessDTO;

public interface IcompanyBusiness {

	public CompanyBusinessDTO save(CompanyBusinessDTO c);
	public Collection<CompanyBusinessDTO> view();
	public CompanyBusinessDTO findById(Long id);
	public void delete(Long id);
	public List<CompanyBusinessDTO> FindCompanyBusinessByDescription(String description );
}
