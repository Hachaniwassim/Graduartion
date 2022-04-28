package app.igesa.validators;
import app.igesa.dto.MetaDTO;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;


public class MetaValidator {
    public static List<String> validate(MetaDTO metaDTO){

        List<String> errors = new ArrayList<>() ;

        if (metaDTO==null) {
            errors.add("Veuillez renseigner le titre  !");
            errors.add("Veuillez renseigner la description !");
            errors.add("Veuillez renseigner Url key !");
            errors.add("Veuillez renseigner le mata Key !");
            errors.add("Veuillez renseigner le produit !");

            return errors;
        }

        if(!StringUtils.hasLength(metaDTO.getMetatitle())) {
            errors.add("Veuillez renseigner le titre  !");
        }
        if(!StringUtils.hasLength(metaDTO.getMetadescription())) {
            errors.add("Veuillez renseigner la description !");
        }
        if(!StringUtils.hasLength(metaDTO.getUrlkey())) {
            errors.add("Veuillez renseigner Url key !");
        }
        if(!StringUtils.hasLength(metaDTO.getMetakey())) {
            errors.add("Veuillez renseigner le mata Key !");
        }
        if(metaDTO.getProduct() == null) {
            errors.add("Veuillez renseigner le produit !");
        }
        return errors;
    }
}
