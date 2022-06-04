package it.igesa.services;
import java.util.Collection;
import java.util.Optional;
import it.igesa.dto.PlateformeDTO;

public interface Iplateforme {
	public PlateformeDTO save(PlateformeDTO p);
	public Collection<PlateformeDTO> getCurrentSiteInfo(Long id_entreprise);
	public Optional<PlateformeDTO>findById(Long id);
	public void delete(Long id);
	public PlateformeDTO updateSiteInfo (PlateformeDTO p);
}
