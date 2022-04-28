package app.igesa.metiers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import app.igesa.dto.CompanyBusinessDTO;
import app.igesa.dto.EntrepriseDTO;
public interface Ientreprise {

	public EntrepriseDTO save(EntrepriseDTO e);
	public Collection<EntrepriseDTO> view();
	public Optional<EntrepriseDTO >findById(Long id);
	//public EntrepriseDTO update(Long id, EntrepriseDTO e);
	public void delete(Long id);
	List<EntrepriseDTO> getEntrepriseByGroupe(Long id );
	public List<EntrepriseDTO> FindEntrepriseByCompanyname(String companyname );

	Thread getCurrentEnterprise();
}
