package app.igesa.metiers.seo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GeneralConfigurationsCDto {
    private Long id;
    private String siteName;
    private String primaryFont;
    private Integer newsletterPageIndex;
    private Integer marketingPageIndex;
    private Integer privacyPageIndex;
    private boolean maintenanceEnabled;
}
