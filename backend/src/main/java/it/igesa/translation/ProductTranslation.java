package it.igesa.translation;

import it.igesa.dto.TranslationObject;
import it.igesa.domaine.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
public class ProductTranslation extends TranslationObject {

    @Column(columnDefinition = "TEXT")
    private String title ;

    @Column(columnDefinition = "TEXT")
    private String detailimage ;

    @Column(columnDefinition = "TEXT")
    private String note ;

    @Column(columnDefinition = "TEXT")
    private String name ;

    @ManyToOne
    @JsonIgnore
    private Product product;

}
