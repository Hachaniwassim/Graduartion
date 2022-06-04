package it.igesa.validators;

import it.igesa.dto.ProductDTO;

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


        return errors;
    }
}
