package app.igesa.strategy.impl;
import app.igesa.dto.CategoryDTO;
import app.igesa.strategy.Strategy;
import com.flickr4java.flickr.FlickrException;
import java.io.InputStream;


public class CategoryServiceImp implements Strategy<CategoryDTO> {


    @Override
    public CategoryDTO savePhoto(InputStream image, String titre, Long id) throws FlickrException {
        return null;
    }
}
