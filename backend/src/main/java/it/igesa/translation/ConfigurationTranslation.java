package it.igesa.translation;

import it.igesa.dto.TranslationObject;
import it.igesa.domaine.ConfigGenerale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Setter
@Getter
public class ConfigurationTranslation extends TranslationObject {
    @Column(columnDefinition = "TEXT")
    private String copyright ;

    @Column(columnDefinition = "TEXT")
    private String title ;

    @Column(columnDefinition = "TEXT")
    private String newslettertitle;

    @Column(columnDefinition = "TEXT")
    private String newslettersubtitle ;
    @ManyToOne
    @JsonIgnore
    private ConfigGenerale config;

    @ManyToOne
    @JoinColumn(name = "config_generale_id")
    private ConfigGenerale configGenerale;

}
