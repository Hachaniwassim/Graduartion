package app.igesa.metiers;

import java.util.Collection;

import app.igesa.dto.GroupeDTO;
import app.igesa.entity.Groupe;
import app.igesa.enumerations.GroupStatus;

public interface Igroupe {
	public GroupeDTO save (GroupeDTO g);
	public Collection<GroupeDTO > view();
	public GroupeDTO findById(Long id);
	public void delete(Long id);
	public void deleteAll();
	public GroupeDTO updateSatus(Long id, GroupStatus status);
	public Groupe getCurrentGroup();

}
