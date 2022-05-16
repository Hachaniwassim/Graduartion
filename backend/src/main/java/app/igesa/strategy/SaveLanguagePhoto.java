package app.igesa.strategy;

import app.igesa.dto.LanguageDTO;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.metiers.Iflicker;
import app.igesa.metiers.Ilanguage;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.io.InputStream;


@Service("LanguageStrategy")
@Slf4j
public class SaveLanguagePhoto implements Strategy<LanguageDTO> {


    private Iflicker iFlickr;
    private Ilanguage ilanguage;

    @Autowired
    public SaveLanguagePhoto(Iflicker iFlickr, Ilanguage ilanguage) {
        this.iFlickr = iFlickr;
        this.ilanguage=ilanguage;
    }

    @Override
    public LanguageDTO savePhoto(InputStream image, String titre, Long id) throws FlickrException {
        LanguageDTO language  = ilanguage.findById(id);
        String Urlphoto = iFlickr.savePhoto(image,titre);
        if(!StringUtils.hasLength(Urlphoto)) {
            throw new InvalideEntityException("Language  image not valid !!", ErrorCode.LANGUAGE_NOT_VALID);
        }
        language.setImage(Urlphoto);
        return ilanguage.save(language);
    }

}
