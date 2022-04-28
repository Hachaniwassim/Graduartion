package app.igesa.strategy.impl;
import app.igesa.dto.ConfigGeneralDTO;
import app.igesa.strategy.Strategy;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.InputStream;



public class ConfigServiceImp implements Strategy<ConfigGeneralDTO> {


    @Override
    public ConfigGeneralDTO savePhoto(InputStream image, String titre, Long id) throws FlickrException {
        return null;
    }
}

