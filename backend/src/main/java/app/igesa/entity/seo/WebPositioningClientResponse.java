package app.igesa.entity.seo;

import app.igesa.enumerations.RobotsMetaTag;
import lombok.*;
import java.util.Set;

/**
 * Tarchoun Abir
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebPositioningClientResponse {
    private String title;
    private String description;
    private Set<RobotsMetaTag> robotsMetaTags;
}
