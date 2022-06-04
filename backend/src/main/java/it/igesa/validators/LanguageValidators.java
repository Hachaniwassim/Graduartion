package it.igesa.validators;

import it.igesa.dto.LanguageDTO;

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


        return errors;
    }
}
