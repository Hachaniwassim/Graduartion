package app.igesa.entity.seo;

import javax.persistence.*;

import app.igesa.entity.Auditable;
import app.igesa.entity.Entreprise;
import app.igesa.entity.Product;
import app.igesa.enumerations.PagesTypes;
import app.igesa.enumerations.RobotsMetaTag;
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
@Table(name="Meta")
public class Meta  extends Auditable {
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
		private  Entreprise entreprise ;

}
