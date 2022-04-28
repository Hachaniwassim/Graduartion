package app.igesa.strategy;

import app.igesa.dto.PostDTO;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.metiers.FlickrImpl;
import app.igesa.metiers.Iflicker;
import app.igesa.metiers.Ipost;
import app.igesa.metiers.PostImp;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service("PostStrategy")
@Slf4j
public class SavePostPhoto implements Strategy<PostDTO> {
    private Ipost ipost;
    private Iflicker iFlickr;
    @Autowired
    public SavePostPhoto(Ipost ipost, Iflicker iFlickr) {
        this.ipost = ipost;
        this.iFlickr = iFlickr;
    }


    @Override
    public PostDTO savePhoto(InputStream image, String titre, Long id) throws FlickrException {
        PostDTO post = ipost.findById(id);
        String Urlphoto = iFlickr.savePhoto(image,titre);
        if(!StringUtils.hasLength(Urlphoto)) {
            throw new InvalideEntityException("Post image not valid", ErrorCode.POST_IMAGE_NOT_VALID);
        }
        post.setImage(Urlphoto);
        return ipost.save(post);
    }
    }

