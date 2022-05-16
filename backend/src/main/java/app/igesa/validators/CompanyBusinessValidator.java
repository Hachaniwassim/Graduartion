package app.igesa.validators;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import app.igesa.dto.CompanyBusinessDTO;


public class CompanyBusinessValidator {
	public static List<String> validate (CompanyBusinessDTO companyDTO){
		List<String> errors = new ArrayList<>() ;
		if(companyDTO == null || !StringUtils.hasLength(companyDTO.getDomainename())) {
		  errors.add("Veuillez renseigner le Domaine Name de l'entreprise  !");
	  }
		return errors;

}
}