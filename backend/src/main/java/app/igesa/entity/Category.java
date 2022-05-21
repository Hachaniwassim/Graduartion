package app.igesa.entity;
import javax.persistence.*;
import app.igesa.translation.CategoryTranslation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
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
	private String title;
	private String description ;
	private String name ;
	private String menuimage ;
	private String bannerimage ;

	/**
	 *
	 * Entreprise
	 *
	 */

	@ManyToOne
	private Entreprise enterprise;
	@Column(columnDefinition = "int default 1") // used to srt categories in FO of client
	private int priority;
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private List<Product> products = new ArrayList<>();

	@ManyToOne(optional = true)
	@JsonIgnoreProperties(value = {"subCategoryList"}, allowSetters = true)
	private Category parent;

	@OneToMany(mappedBy = "parent")
	@JsonIgnoreProperties(value = {"parent"}, allowSetters = true)
	@OrderBy("priority ASC")
	private List<Category> subCategoryList;

	@OneToMany(mappedBy="category",cascade = CascadeType.ALL)
	private List<CategoryTranslation> categoryTranslations ;

	@ManyToOne
	@JoinColumn(name = "entreprise_id")
	private Entreprise entreprise;

}
