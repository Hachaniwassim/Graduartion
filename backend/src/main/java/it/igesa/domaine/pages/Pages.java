package it.igesa.domaine.pages;
import javax.persistence.*;
import it.igesa.domaine.Auditable;
import it.igesa.domaine.Entreprise;
import it.igesa.domaine.FormEntity;
import it.igesa.enumerations.PagesTypes;
import it.igesa.translation.PagesTranslations;
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
	@Type(type = "org.hibernate.type.TextType")
	private String title ;
	@Column(columnDefinition="text")
	private String description ;
	@Column(columnDefinition="text")
	private String textbutton;
	@Column(columnDefinition="text")
	private String question ;

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
