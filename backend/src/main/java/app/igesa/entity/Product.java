package app.igesa.entity;

/**
 *
 * @author Tarchoun Abir
 *
 */
import javax.persistence.*;

import app.igesa.entity.seo.Meta;
import app.igesa.enumerations.ProductTypes;
import app.igesa.translation.ProductTranslation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper= true)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table (name="Product")
public class Product extends Auditable{
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	private String title ;
	private String Slug ;
	private String name ;
	@Column(columnDefinition="text")
	private String description;
	@Column(columnDefinition="text")
	private String caracteristique;
	@Column(columnDefinition="text")
	private String requirements;
	private String image ;

	/**
	 * ENTERPRISE
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	private Entreprise entreprise;

	/**
	 * CATEGORIES
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;

	/**
	 * TAGS
	 */

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "products_tags",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tags> tags = new HashSet<>();


	/**
	 * SEO
	 */

	/*private String urlKey;
	private String metaTitle;
	private String metaKey;
	private String metaDescription;*/


}
