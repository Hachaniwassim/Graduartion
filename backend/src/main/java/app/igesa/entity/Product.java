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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper= true)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table (name="Product")
public class Product extends Auditable{
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	private String title ;
	private String detailsimage ;
	private String note ;
	private String name ;
	private String description;
	@Column(name="Image")
	private String image ;
	@Enumerated(EnumType.STRING)
	private ProductTypes type;
	/**
	 * Translation :: still not use
	 */

	@OneToMany(mappedBy="product",cascade = CascadeType.ALL)
	private List<ProductTranslation> productTranslations;
	/**
	 * ENTERPRISE
	 */

	@ManyToOne
	private Entreprise entreprise;

	/**
	 * CATEGORIES
	 */

	@ManyToOne
	private Category category;
	/**
	 * SEO
	 */

	private String urlKey;
	private String metaTitle;
	private String metaKey;
	private String metaDescription;

    /**
    * TAGS
    */

     @ManyToOne
     private Tags tags;

}
