package app.igesa.metiers;
import java.util.Collection;
import java.util.Optional;
import app.igesa.dto.PlateformeDTO;

public interface Iplateforme {
	public PlateformeDTO save(PlateformeDTO p);
	public Collection<PlateformeDTO> getCurrentSiteInfo();
	public Optional<PlateformeDTO>findById(Long id);
	public void delete(Long id);
	public PlateformeDTO updateSiteInfo (PlateformeDTO p);
}
