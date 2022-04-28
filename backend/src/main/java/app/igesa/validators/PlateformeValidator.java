package app.igesa.validators;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import app.igesa.dto.PlateformeDTO;

public class PlateformeValidator {
	public static List<String> validate (PlateformeDTO plateformeDTO){
		List<String> errors = new ArrayList<>() ;
		if(plateformeDTO== null ) {

			  errors.add("Veuillez renseigner l'adresse du Plateforme !");
			  errors.add("Veuillez renseigner le Numero du Plateforme !");
			  errors.add("Veuillez renseigner l Email du Plateforme !");
			  return errors;
		}
		if(!StringUtils.hasLength(plateformeDTO.getAdresse())) {
		  errors.add("Veuillez renseigner l'adresse du Plateforme !");
		}
		
		if( !StringUtils.hasLength(plateformeDTO.getEmail()));{
		  errors.add("Veuillez renseigner l Email du Plateforme !");
		}
		if(!StringUtils.hasLength(plateformeDTO.getPhone()));{
			  errors.add("Veuillez renseigner le Numero du Plateforme !");}
		return errors;
}

}