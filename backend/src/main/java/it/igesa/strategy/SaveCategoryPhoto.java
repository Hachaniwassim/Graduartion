package it.igesa.strategy;

import it.igesa.dto.CategoryDTO;
import it.igesa.enumerations.ErrorCode;
import it.igesa.exceptions.InvalideEntityException;
import it.igesa.services.Icategory;
import it.igesa.services.Iflicker;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.io.InputStream;

@Service("CategoryStrategy")
@Slf4j
public class SaveCategoryPhoto implements Strategy<CategoryDTO> {
    private Iflicker iFlickr;
    private Icategory icategory;

    @Autowired
    public SaveCategoryPhoto(Iflicker iFlickr, Icategory icategory) {
        this.iFlickr = iFlickr;
        this.icategory = icategory;
    }



    @Override
    public CategoryDTO savePhoto(InputStream image, String titre, Long id) throws FlickrException {
        CategoryDTO category =icategory.findById(id);
        String Urlphoto = iFlickr.savePhoto(image,titre);
        if(!StringUtils.hasLength(Urlphoto)) {
            throw new InvalideEntityException("Category image not valid", ErrorCode.CATEGORY_IMAGE_NOT_VALID);
        }
        category.setImage(Urlphoto);
        return icategory.save(category);
    }
}

