package app.igesa.dto.seo;
import app.igesa.enumerations.PagesTypes;
import app.igesa.enumerations.RobotsMetaTag;
import lombok.*;
import java.util.Set;

/**
 * @author Tarchoun Abir
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebPositioningDto {
    private PagesTypes page;
    private Set<RobotsMetaTag> robots;
}
