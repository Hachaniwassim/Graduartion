package app.igesa.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import app.igesa.entity.Language;
import app.igesa.enumerations.LangEnum;
import lombok.Getter;
import lombok.Setter;

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
        this.enumLanguage= language != null ? language.getLang() : null;
        this.language = language;
    }

}
