package it.igesa.validators;

import it.igesa.dto.TagsDTO;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class TagsValidator {
    public static List<String> validate(TagsDTO tagsDTO){

        List<String> errors = new ArrayList<>() ;

        if (tagsDTO==null) {
            errors.add("Veuillez renseigner la description !");
            errors.add("Veuillez renseigner le produit !");

            return errors;
        }

        if(!StringUtils.hasLength(tagsDTO.getDescription())) {
            errors.add("Veuillez renseigner la description  !");
        }

        return errors;
    }
}
