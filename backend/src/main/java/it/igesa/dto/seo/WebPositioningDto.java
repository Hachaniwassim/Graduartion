package it.igesa.dto.seo;
import it.igesa.domaine.Entreprise;
import it.igesa.domaine.seo.RobotsMetaTagConverter;
import it.igesa.domaine.seo.Seo;
import it.igesa.enumerations.PagesTypes;
import it.igesa.enumerations.RobotsMetaTag;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * @author Tarchoun Abir
 *
 *
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebPositioningDto  {
    private Long id;
    private String title;
    private  String keywords;
    @ElementCollection
    @Convert(converter = RobotsMetaTagConverter.class)
    private Set<RobotsMetaTag> robots= new HashSet<>() ;
    private PagesTypes page;
    protected Date createdDate;
    protected Date lastModifiedDate;
    private Long entrepriseId;
    public static WebPositioningDto fromEntity(Seo seo) {

        return WebPositioningDto.builder()
                .id(seo.getId())
                .title(seo.getTitle())
                .keywords(seo.getKeywords())
                .robots(seo.getRobots())
                .page(seo.getPage())
                .lastModifiedDate(seo.getLastModifiedDate())
                .entrepriseId(seo.getEntreprise().getId())
                .createdDate(seo.getCreatedDate())
                .build();
    }

    public static Seo toEntity(WebPositioningDto dto) {

        Seo seo = new Seo();
        seo.setId(dto.getId());
        seo.setTitle(dto.getTitle());
        seo.setCreatedDate(dto.getCreatedDate());
        seo.setLastModifiedDate(dto.getLastModifiedDate());
        seo.setRobots(dto.getRobots());
        seo.setKeywords(dto.getKeywords());
        seo.setPage(dto.getPage());
        //===========================> Entreprise
        Entreprise entreprise = new Entreprise();
        entreprise.setId(dto.getEntrepriseId());
       seo.setEntreprise(entreprise);
        return seo;
    }

}
