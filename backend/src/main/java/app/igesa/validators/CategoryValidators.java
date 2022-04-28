package app.igesa.validators;

import app.igesa.dto.CategoryDTO;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class CategoryValidators {
    public static List<String> validateCategory (CategoryDTO categoryDTO){

        List<String> errors = new ArrayList<>() ;

        if (categoryDTO ==null) {

            errors.add("Veuillez renseigner la description !");
            errors.add("Veuillez renseigner le title de la categorie !");
            errors.add("Veuillez renseigner l'image !");

            return errors;
        }

        if(!StringUtils.hasLength(categoryDTO.getDescription())) {
            errors.add("Veuillez renseigner la description !");
        }
        if(!StringUtils.hasLength(categoryDTO.getTitle())) {
            errors.add("Veuillez renseigner le title de la categorie !");
        }

       /* if(categoryDTO.getImage() == null) {
            errors.add("Veuillez renseigner l'image !");
        }*/
        return errors;
    }
}
