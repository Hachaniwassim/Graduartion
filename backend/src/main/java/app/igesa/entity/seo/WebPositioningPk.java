package app.igesa.entity.seo;
import app.igesa.enumerations.PagesTypes;
import lombok.*;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * @author Tarchoun Abir
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@Embeddable
public class WebPositioningPk implements Serializable {
    @Enumerated(EnumType.STRING)
    private PagesTypes page;
    private Long enterprise;
}
