package app.igesa.translation;

import app.igesa.dto.TranslationObject;
import app.igesa.entity.Pages;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter
public class PagesTranslations extends TranslationObject {
    @ManyToOne
    @JoinColumn(name = "pages_id")
    private Pages pages;

    @Column(columnDefinition = "TEXT")
    private String title ;

    @Column(columnDefinition = "TEXT")
    private String description ;

}
