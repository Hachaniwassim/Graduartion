package app.igesa.metiers;

import java.util.Collection;

import app.igesa.dto.GroupeDTO;

public interface Igroupe {
	public GroupeDTO save (GroupeDTO g);
	public Collection<GroupeDTO > view();
	public GroupeDTO findById(Long id);
	public void delete(Long id);
	GroupeDTO findAllById(Long id);
     public void deleteAll();
}
