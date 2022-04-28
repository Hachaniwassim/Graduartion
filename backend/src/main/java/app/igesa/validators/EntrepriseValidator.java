package app.igesa.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import app.igesa.dto.EntrepriseDTO;

public class EntrepriseValidator {
	public static List<String> validateEntreprise (EntrepriseDTO entrepriseDTO){
		
		List<String> errors = new ArrayList<>() ;
		
		if (entrepriseDTO==null) {
			
		   errors.add("Veuillez renseigner le code Fiscale de l'entreprise  !");
           errors.add("Veuillez renseigner le numero de l'entreprise !");
		   errors.add("Veuillez renseigner l Email de l'entreprise !");
		   errors.add("Veuillez renseigner le Nom de l'entreprise !");
		   errors.add("Veuillez renseigner le Groupe de l'entreprise !");
		   
		   return errors;
		}
		
		if(!StringUtils.hasLength(entrepriseDTO.getCodefiscale())) {
		  errors.add("Veuillez renseigner le code Fiscale de l'entreprise  !");
	  }
		 if(!StringUtils.hasLength(entrepriseDTO.getPhone())) {
			  errors.add("Veuillez renseigner le numero de l'entreprise !");
		}
		 if(!StringUtils.hasLength(entrepriseDTO.getEmail())) {
			  errors.add("Veuillez renseigner l Email de l'entreprise !");
		}
	     if(!StringUtils.hasLength(entrepriseDTO.getCompanyname())) {
			  errors.add("Veuillez renseigner le Nom de l'entreprise !");
		}
	     if(entrepriseDTO.getGroupe() == null) {
			  errors.add("Veuillez renseigner le Groupe de l'entreprise !");
		}
  return errors;
}
}
