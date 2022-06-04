package it.igesa.strategy.impl;
import it.igesa.dto.CategoryDTO;
import it.igesa.strategy.Strategy;
import com.flickr4java.flickr.FlickrException;
import java.io.InputStream;


public class CategoryServiceImp implements Strategy<CategoryDTO> {


    @Override
    public CategoryDTO savePhoto(InputStream image, String titre, Long id) throws FlickrException {
        return null;
    }
}
