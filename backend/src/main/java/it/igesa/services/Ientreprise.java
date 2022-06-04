package it.igesa.services;
import java.util.Collection;
import java.util.List;

import it.igesa.dto.EntrepriseDTO;
import it.igesa.domaine.Entreprise;

public interface Ientreprise {

	public EntrepriseDTO save(EntrepriseDTO e);
	public Collection<EntrepriseDTO> view();
	public EntrepriseDTO findById(Long id);
	public void delete(Long id);
	List<EntrepriseDTO> getEntrepriseByGroupe(Long id );
	public List<EntrepriseDTO> FindEntrepriseByCompanyname(String companyname );
	public Entreprise getCurrentEnterprise();


    //public Long countEntreprise();
}
