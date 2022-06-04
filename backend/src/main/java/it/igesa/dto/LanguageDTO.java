package it.igesa.dto;

import it.igesa.domaine.Entreprise;
import it.igesa.domaine.Language;
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
    private String code;
    private String name;
    protected Date createdDate;
    protected Date lastModifiedDate;
    private Long entrepriseId;


    public static LanguageDTO fromEntity(Language language) {
        if ( language== null) {
            return null;
        }
        return LanguageDTO.builder()
                .id(language.getId())
                .code(language.getCode())
                .name(language.getName())
                .createdDate(language.getCreatedDate())
                .lastModifiedDate(language.getLastModifiedDate())
                .entrepriseId(language.getEntreprise().getId())
                .build();
    }

    public static Language toEntity(LanguageDTO dto) {

        if (dto == null) {
            return null;
        }

        Language language= new Language();
        language.setId(dto.getId());
        language.setCode(dto.getCode());
        language.setName(dto.getName());
        language.setCreatedDate(dto.getCreatedDate());
        language.setLastModifiedDate(dto.getLastModifiedDate());

        //===========================> Entreprise
        Entreprise entreprise = new Entreprise();
        entreprise.setId(dto.getEntrepriseId());
        language.setEntreprise(entreprise);
        return language;
    }

}
