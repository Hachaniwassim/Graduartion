package it.igesa.strategy;

import it.igesa.dto.LanguageDTO;
import it.igesa.enumerations.ErrorCode;
import it.igesa.exceptions.InvalideEntityException;
import it.igesa.services.Iflicker;
import it.igesa.services.Ilanguage;
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
        //language.setImage(Urlphoto);
        return ilanguage.save(language);
    }

}
