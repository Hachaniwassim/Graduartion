package it.igesa.services;

import it.igesa.dto.ConfigGeneralDTO;
import java.util.Collection;

public interface Iconfiguration {
    public ConfigGeneralDTO save (ConfigGeneralDTO c);
    public Collection<ConfigGeneralDTO> view(Long enterprise_id);
    public ConfigGeneralDTO findById(Long id);
    public void delete(Long id);
}
