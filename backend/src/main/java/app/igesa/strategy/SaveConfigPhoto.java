package app.igesa.strategy;

import app.igesa.dto.ConfigGeneralDTO;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.metiers.Iconfiguration;
import app.igesa.metiers.Iflicker;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.io.InputStream;

@Service("ConfigStrategy")
@Slf4j
public class SaveConfigPhoto implements Strategy<ConfigGeneralDTO> {


    private Iflicker iflicker;
    private Iconfiguration iconfiguration;


    @Autowired
    public SaveConfigPhoto(Iflicker iflicker, Iconfiguration iconfiguration) {
        this.iflicker = iflicker;
        this.iconfiguration=iconfiguration;
    }

    @Override
    public ConfigGeneralDTO savePhoto(InputStream image, String titre, Long id) throws FlickrException {

        ConfigGeneralDTO config  =iconfiguration.findById(id);
        String Urlphoto = iflicker.savePhoto(image,titre);
        if(!StringUtils.hasLength(Urlphoto)) {
            throw new InvalideEntityException("Config image not valid", ErrorCode.CONFIG_IMAGE_NOT_VALID);
        }
        config.setImage(Urlphoto);
        return iconfiguration.save(config);
    }
}
