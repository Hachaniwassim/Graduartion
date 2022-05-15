package app.igesa.metiers;

import java.util.Collection;
import java.util.Optional;

import app.igesa.dto.PlateformeDTO;
import app.igesa.entity.Plateforme;

public interface Iplateforme {
	public PlateformeDTO save(PlateformeDTO p);
	public Collection<PlateformeDTO> view();
	public Optional<PlateformeDTO>findById(Long id);
	public void delete(Long id);
	public PlateformeDTO update ( Long id , PlateformeDTO p);

}
