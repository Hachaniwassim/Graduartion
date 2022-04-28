package app.igesa.metiers;

import app.igesa.dto.ConfigGeneralDTO;
import app.igesa.dto.PlateformeDTO;

import java.util.Collection;
import java.util.Optional;

public interface Iconfiguration {
    public ConfigGeneralDTO save (ConfigGeneralDTO c);
    public Collection<ConfigGeneralDTO> view();
    public ConfigGeneralDTO findById(Long id);
    public void delete(Long id);
    public ConfigGeneralDTO update(ConfigGeneralDTO c , Long id);
}
