package app.igesa.entity.seo;

import javax.persistence.*;

import app.igesa.entity.Auditable;
import app.igesa.entity.Entreprise;
import app.igesa.entity.Product;
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

        @GeneratedValue(strategy= GenerationType.IDENTITY)
		@EmbeddedId
		private WebPositioningPk id;
		private String title;
		@Lob
		@Type(type = "org.hibernate.type.TextType")
		private String description;
		@ElementCollection
		@Convert(converter = RobotsMetaTagConverter.class)
		private Set<RobotsMetaTag> robots;
		@ManyToOne(optional = false)
		@MapsId("enterprise")
		private Entreprise enterprise;


	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product ;
}
