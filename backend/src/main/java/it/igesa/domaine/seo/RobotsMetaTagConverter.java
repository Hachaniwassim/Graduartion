package it.igesa.domaine.seo;
import it.igesa.enumerations.RobotsMetaTag;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * @author Tarchoun Abir
 */
@Converter
public class RobotsMetaTagConverter implements AttributeConverter<RobotsMetaTag, String> {

    @Override
    public String convertToDatabaseColumn(RobotsMetaTag attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getTag();
    }

    @Override
    public RobotsMetaTag convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(RobotsMetaTag.values())
                .filter(u -> u.getTag().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
