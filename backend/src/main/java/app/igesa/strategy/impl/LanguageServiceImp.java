package app.igesa.strategy.impl;
import app.igesa.dto.LanguageDTO;
import app.igesa.strategy.Strategy;
import com.flickr4java.flickr.FlickrException;
import java.io.InputStream;


public class LanguageServiceImp implements Strategy<LanguageDTO> {


    @Override
    public LanguageDTO savePhoto(InputStream image, String titre, Long id) throws FlickrException {
        return null;
    }
}
