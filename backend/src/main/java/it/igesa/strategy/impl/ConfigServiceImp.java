package it.igesa.strategy.impl;
import it.igesa.dto.ConfigGeneralDTO;
import it.igesa.strategy.Strategy;
import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;



public class ConfigServiceImp implements Strategy<ConfigGeneralDTO> {


    @Override
    public ConfigGeneralDTO savePhoto(InputStream image, String titre, Long id) throws FlickrException {
        return null;
    }
}

