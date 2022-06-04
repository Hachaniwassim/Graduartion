package it.igesa.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import it.igesa.domaine.Language;
import it.igesa.enumerations.LangEnum;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Tarchoun Abir
 *
 */

@Getter
@Setter
@MappedSuperclass
public class TranslationObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LangEnum enumLanguage ;
    @ManyToOne
    private Language language;
    public void setLanguage(Language language) {
        this.enumLanguage= language != null ? LangEnum.valueOf(language.getCode()) : null;
        this.language = language;
    }

}
