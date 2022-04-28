package app.igesa.validators;

import app.igesa.dto.MetaDTO;
import app.igesa.dto.PageDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class pageValidator {
    public static List<String> validate(PageDTO pageDTO){

        List<String> errors = new ArrayList<>() ;

        if (pageDTO==null) {
            errors.add("Veuillez renseigner le titre  !");
            errors.add("Veuillez renseigner la description !");
            errors.add("Veuillez renseigner lentreprise!");

            return errors;
        }

        if(!StringUtils.hasLength(pageDTO.getTitle())) {
            errors.add("Veuillez renseigner le titre  !");
        }
        if(!StringUtils.hasLength(pageDTO.getDescription())) {
            errors.add("Veuillez renseigner la description !");
        }
        if(pageDTO.getDescription()== null) {
            errors.add("Veuillez renseigner lentreprise!");
        }
        return errors;
    }
}
