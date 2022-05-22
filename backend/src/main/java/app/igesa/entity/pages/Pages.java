package app.igesa.entity.pages;
import javax.persistence.*;
import app.igesa.entity.Auditable;
import app.igesa.entity.Entreprise;
import app.igesa.enumerations.PagesTypes;
import app.igesa.translation.PagesTranslations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


/**
 *
 * @author Tarchoun Abir
 *
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Pages")
public class Pages extends Auditable {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	private PagesTypes pagetype;
	@Column(columnDefinition="text")
	private String title ;
	@Column(columnDefinition="text")
	private String description ;

	/**
	 * Entreprise
	 */
	@ManyToOne
	private Entreprise entreprise;

	/**
	 * translation
	 */
	@OneToMany(mappedBy="pages",cascade = CascadeType.ALL)
	private List<PagesTranslations> pagesTranslations;
}
