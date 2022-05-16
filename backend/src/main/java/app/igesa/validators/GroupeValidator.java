package app.igesa.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import app.igesa.dto.GroupeDTO;

public class GroupeValidator {
	public static List<String> validate (GroupeDTO groupeDTO){
		List<String> errors = new ArrayList<>() ;

		if (groupeDTO==null) {

			errors.add("Veuillez renseigner le Nom du groupe !");
			errors.add("Veuillez renseigner le champ company Business !");

			return errors;
		}
		if( !StringUtils.hasLength(groupeDTO.getName()));{
		  errors.add("Veuillez renseigner le Nom du Groupe !");
	  }
		if( groupeDTO.getCompanyBusiness()==null);{
			errors.add("Veuillez renseigner le champ company Business !");
		}
		return errors;
}
}