package app.igesa.validators;

import app.igesa.dto.ConfigGeneralDTO;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class ConfigValidator {


    public static List<String> validateConfig (ConfigGeneralDTO configDTO){

        List<String> errors = new ArrayList<>() ;

        if (configDTO==null) {

            errors.add("Veuillez renseigner l'Email !");
            errors.add("Veuillez renseigner le numero de telephone !");
            errors.add("Veuillez renseigner l adresse !");
            errors.add("Veuillez renseigner l'entreprise !");
            //errors.add("Veuillez uploder le logo !");

            return errors;
        }

        if(!StringUtils.hasLength(configDTO.getEmail())) {
            errors.add("Veuillez renseigner l'Email !");
        }
        if(!StringUtils.hasLength(configDTO.getPhone())) {
            errors.add("Veuillez renseigner le numero de telephone !");
        }
        if(!StringUtils.hasLength(configDTO.getAdresse())) {
            errors.add("Veuillez renseigner l adresse !");
        }
       // if(!StringUtils.hasLength(configDTO.getLogo())) {
         //   errors.add("Veuillez uploder le logo !");
       // }
        if(configDTO.getEntreprise()== null) {
            errors.add("Veuillez renseigner l'entreprise !");
        }
        return errors;
    }
}
