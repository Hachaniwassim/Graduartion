package app.igesa.translation;

import app.igesa.dto.TranslationObject;
import app.igesa.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class PostTranslation extends TranslationObject {



    @ManyToOne
    @JsonIgnore
    private Post post;

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
