package it.igesa.services;

import java.util.Collection;

import it.igesa.dto.GroupeDTO;
import it.igesa.domaine.Groupe;
import it.igesa.enumerations.GroupStatus;

public interface Igroupe {
	public GroupeDTO save (GroupeDTO g);
	public Collection<GroupeDTO > view();
	public GroupeDTO findById(Long id);
	public void delete(Long id);
	public void deleteAll();
	public GroupeDTO updateSatus(Long id, GroupStatus status);
	public Groupe getCurrentGroup();

}
