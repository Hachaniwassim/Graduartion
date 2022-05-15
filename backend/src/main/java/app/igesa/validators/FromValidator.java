package app.igesa.validators;

import app.igesa.dto.FormDTO;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class FromValidator {
    public static List<String> validate (FormDTO formDTO){

        List<String> errors = new ArrayList<>() ;

        if (formDTO==null) {

            errors.add("Veuillez renseigner le champ nationality !");
            errors.add("Veuillez renseigner le champ referent!");
            errors.add("Veuillez renseigner le champ email !");
            errors.add("Veuillez renseigner le champ adresse !");
            errors.add("Veuillez renseigner le champ fax!");
            errors.add("Veuillez renseigner le champ  Name!");
            errors.add("Veuillez renseigner le champ  Company Name!");

            return errors;
        }

        if(!StringUtils.hasLength(formDTO.getCompanyname())) {
            errors.add("Veuillez renseigner le champ companyname !");
        }
        if(!StringUtils.hasLength(formDTO.getEmail())) {
            errors.add("Veuillez renseigner le champ de l'email !");
        }
        if(!StringUtils.hasLength(formDTO.getAdresse())) {
            errors.add("Veuillez renseigner le champ adresse !");
        }
        if(!StringUtils.hasLength(formDTO.getFax())) {
            errors.add("Veuillez renseigner le champ fax  !");
        }
        if(!StringUtils.hasLength(formDTO.getMobile())) {
            errors.add("Veuillez renseigner le champ  mobile !");
        }
        if(!StringUtils.hasLength(formDTO.getNationality())) {
            errors.add("Veuillez renseigner le champ  nationality!");
        }
        if(!StringUtils.hasLength(formDTO.getReferent())) {
            errors.add("Veuillez renseigner le champ  referent!");
        }
        if(!StringUtils.hasLength(formDTO.getName())) {
            errors.add("Veuillez renseigner le champ  Name!");
        }
        return errors;
    }
}
