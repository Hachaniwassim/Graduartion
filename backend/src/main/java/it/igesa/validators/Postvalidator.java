package it.igesa.validators;
import it.igesa.dto.PostDTO;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class Postvalidator {
    public static List<String> validate (PostDTO postDTO){

        List<String> errors = new ArrayList<>() ;

        if (postDTO==null) {

            errors.add("Veuillez renseigner le champ content !");
            errors.add("Veuillez renseigner le champ description !");
            errors.add("Veuillez renseigner le champ slug!");
            errors.add("Veuillez renseigner le champ titre!");
            errors.add("Veuillez renseigner le champs types !");
            return errors;
        }

        if(!StringUtils.hasLength(postDTO.getTitle())) {
            errors.add("Veuillez renseigner le champ title !");
        }

        return errors;
    }

}
