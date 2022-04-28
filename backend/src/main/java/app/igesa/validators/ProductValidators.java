package app.igesa.validators;

import app.igesa.dto.PostDTO;
import app.igesa.dto.ProductDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductValidators {
    public static List<String> validate (ProductDTO productDTO){

        List<String> errors = new ArrayList<>() ;

        if (productDTO==null) {

            errors.add("Veuillez renseigner le champ titre !");
            errors.add("Veuillez renseigner le champ name !");
            errors.add("Veuillez renseigner le champ details!");
            errors.add("Veuillez uploder une image!");
            errors.add("Veuillez renseigner la category !");

            return errors;
        }

        if(!StringUtils.hasLength(productDTO.getTitle())) {
            errors.add("Veuillez renseigner le champ title  !");
        }
        if(!StringUtils.hasLength(productDTO.getName())) {
            errors.add("Veuillez renseigner le champ name !");
        }
        if(!StringUtils.hasLength(productDTO.getDetailimage())) {
            errors.add("Veuillez renseigner le champ details!");
        }
        if(!StringUtils.hasLength(productDTO.getImage())) {
            errors.add("Veuillez uploder une image !");
        }
        if(productDTO.getCategory()== null) {
            errors.add("Veuillez renseigner la categorie !");
        }
        return errors;
    }
}
