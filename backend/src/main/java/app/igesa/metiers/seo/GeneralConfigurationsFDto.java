package app.igesa.metiers.seo;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GeneralConfigurationsFDto extends GeneralConfigurationsCDto {
    private String maintenanceTitle;
    private String maintenanceText;
}
