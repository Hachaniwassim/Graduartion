package app.igesa.dto;

import app.igesa.entity.Language;
import app.igesa.enumerations.LangEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Tarchoun Abir
 */

@Data
@Builder
public class LanguageDTO {
    private Long id;
    private LangEnum lang;
    private String name;
    private String image;
    private boolean active;
    protected Date createdDate;
    protected Date lastModifiedDate;


    public static LanguageDTO fromEntity(Language language) {
        if ( language== null) {
            return null;
        }
        return LanguageDTO.builder()
                .id(language.getId())
                .active(language.isActive())
                .lang(language.getLang())
                .name(language.getName())
                .image(language.getFlag())
                .createdDate(language.getCreatedDate())
                .lastModifiedDate(language.getLastModifiedDate())
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
        language.setFlag(dto.getImage());
        language.setName(dto.getName());
        language.setCreatedDate(dto.getCreatedDate());
        language.setLastModifiedDate(dto.getLastModifiedDate());
        return language;
    }

}
