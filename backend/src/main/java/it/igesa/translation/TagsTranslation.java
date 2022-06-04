package it.igesa.translation;

import it.igesa.dto.TranslationObject;
import it.igesa.domaine.Tags;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class TagsTranslation extends TranslationObject {


    @Column(columnDefinition = "TEXT")
    private String name ;

    @Column(columnDefinition = "TEXT")
    private String description ;

    @ManyToOne
    @JsonIgnore
    private Tags tags;

}
