package app.igesa.entity.pages;
import javax.persistence.*;
import app.igesa.entity.Auditable;
import app.igesa.entity.Entreprise;
import app.igesa.enumerations.PagesTypes;
import app.igesa.translation.PagesTranslations;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.List;


/**
 *
 * @author Tarchoun Abir
 *
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Pages")
public class Pages extends Auditable {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	private PagesTypes pagetype;
	@Type(type = "org.hibernate.type.TextType")
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
