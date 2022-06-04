package it.igesa.strategy;

import it.igesa.dto.ProductDTO;
import it.igesa.enumerations.ErrorCode;
import it.igesa.exceptions.InvalideEntityException;
import it.igesa.services.Iflicker;
import it.igesa.services.Iproduct;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service("ProductStrategy")
@Slf4j
public class SaveProductPhoto implements  Strategy<ProductDTO>{

     private Iflicker iflicker;
     private Iproduct iproduct;
    @Autowired
    public SaveProductPhoto(Iflicker iflicker, Iproduct iproduct) {
        this.iflicker = iflicker;
        this.iproduct = iproduct;
    }


    @Override
    public ProductDTO savePhoto(InputStream image, String titre, Long id) throws FlickrException {
        ProductDTO product =iproduct.findById(id);
        String Urlphoto = iflicker.savePhoto(image,titre);
        if(!StringUtils.hasLength(Urlphoto)) {
            throw new InvalideEntityException("Product image not valid", ErrorCode.PRODUCT_IMAGE_NOT_VALID);
        }
        product.setImage(Urlphoto);
        return iproduct.save(product);
    }
}

