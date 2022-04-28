package app.igesa.translation;

import app.igesa.dto.CategoryDTO;
import app.igesa.dto.TranslationObject;
import app.igesa.entity.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Locale;


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
