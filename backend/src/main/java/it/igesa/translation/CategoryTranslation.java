package it.igesa.translation;

import it.igesa.dto.TranslationObject;
import it.igesa.domaine.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter
public class CategoryTranslation extends TranslationObject {


    @Column(columnDefinition = "TEXT")
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description ;
    @ManyToOne
    @JsonIgnore
    private Category category;

}
