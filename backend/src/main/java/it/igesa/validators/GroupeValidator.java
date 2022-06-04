package it.igesa.validators;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import it.igesa.dto.GroupeDTO;

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

		return errors;
}
}