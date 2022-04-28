package app.igesa.validators;

import app.igesa.dto.LanguageDTO;
import app.igesa.dto.MetaDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LanguageValidators {
    public static List<String> validate(LanguageDTO languageDTO){

        List<String> errors = new ArrayList<>() ;

        if (languageDTO==null) {
            errors.add("Veuillez renseigner le nom !");
            errors.add("Veuillez renseigner le champs flag!");
            errors.add("Veuillez renseigner le champs lang !");

            return errors;
        }

        if(!StringUtils.hasLength(languageDTO.getImage())) {
            errors.add("Veuillez renseigner le champs flag  !");
        }
        if(!StringUtils.hasLength(languageDTO.getName())) {
            errors.add("Veuillez renseigner le nom !");
        }

        if(languageDTO.getLang() == null) {
            errors.add("Veuillez renseigner la lang!");
        }
        return errors;
    }
}
