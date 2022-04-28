package app.igesa.dto;

import app.igesa.entity.Entreprise;
import app.igesa.entity.Language;
import app.igesa.enumerations.LangEnum;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Data
@Builder
public class LanguageDTO {
    private Long id;
    private LangEnum lang;
    private String name;
    private String image;
    private boolean active;


    public static LanguageDTO fromEntity(Language language) {
        if ( language== null) {
            return null;
        }
        return LanguageDTO.builder()
                .id(language.getId())
                .active(language.isActive())
                .lang(language.getLang())
                .name(language.getName())
                .image(language.getImage())
                .build();
    }

    public static Language toEntity(LanguageDTO dto) {

        if (dto == null) {
            return null;
        }

        Language language= new Language();
        language.setId(dto.getId());
        language.setLang((dto.getLang()));
        language.setActive(dto.isActive());
        language.setImage(dto.getImage());
        language.setName(dto.getName());
        return language;
    }

}
