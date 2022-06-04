package it.igesa.translation;

import it.igesa.dto.TranslationObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PostTranslation extends TranslationObject {



   // @ManyToOne
   // @JsonIgnore
   // private Post post;

    @Column(columnDefinition = "TEXT")
    private String title ;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String content ;

    @Column(columnDefinition = "TEXT")
    private String slug ;

    @Column(columnDefinition = "TEXT")
    private String subtitle ;
}
