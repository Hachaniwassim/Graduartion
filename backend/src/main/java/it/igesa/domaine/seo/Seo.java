package it.igesa.domaine.seo;

import javax.persistence.*;

import it.igesa.domaine.Auditable;
import it.igesa.domaine.Entreprise;
import it.igesa.enumerations.PagesTypes;
import it.igesa.enumerations.RobotsMetaTag;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.Set;

/**
 * @author Tarchoun Abir
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
@Table(name="SEO")
public class Seo extends Auditable {
	    @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
		private Long id;
		private String title;
		private  String keywords;
		@Lob
		@Type(type = "org.hibernate.type.TextType")
		private String description;
		@ElementCollection
		@Convert(converter = RobotsMetaTagConverter.class)
		private Set<RobotsMetaTag> robots;
		private PagesTypes page;
		@ManyToOne(fetch= FetchType.LAZY)
		private Entreprise entreprise ;

}
