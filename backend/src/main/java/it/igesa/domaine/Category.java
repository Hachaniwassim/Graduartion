package it.igesa.domaine;
import javax.persistence.*;
import it.igesa.translation.CategoryTranslation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tarchoun Abir
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name="Category")
public class Category extends Auditable {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	private String image ;
	@Type(type = "org.hibernate.type.TextType")
	private String title;
	@Column(columnDefinition="text")
	private String description ;
	@Type(type = "org.hibernate.type.TextType")
	private String subtitle;
	private String menuimage ;
	private String bannerimage ;

	/**
	 *
	 * Entreprise
	 *
	 */

	@ManyToOne
	private Entreprise enterprise;

	@JsonIgnore
	@OneToMany(mappedBy="category",cascade = CascadeType.ALL)
	private List<CategoryTranslation> categoryTranslations ;

	@JsonIgnore
	@OneToMany(mappedBy="category")
	private List<Product> products = new ArrayList<>();

	/*@Column(columnDefinition = "int default 1") // used to srt categories in FO of client
	private int priority;


	@ManyToOne(optional = true)
	@JsonIgnoreProperties(value = {"subCategoryList"}, allowSetters = true)
	private Category parent;

	@OneToMany(mappedBy = "parent")
	@JsonIgnoreProperties(value = {"parent"}, allowSetters = true)
	@OrderBy("priority ASC")
	private List<Category> subCategoryList;*/




}
