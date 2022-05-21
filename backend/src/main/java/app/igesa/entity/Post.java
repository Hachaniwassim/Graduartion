package app.igesa.entity;
import javax.persistence.*;
import app.igesa.entity.pages.Pages;
import app.igesa.enumerations.Types;
import app.igesa.translation.PostTranslation;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;


/**
 *
 * @author Tarchoun Abir
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Post")
@EqualsAndHashCode(callSuper= true)
@EntityListeners(AuditingEntityListener.class)
public class Post extends Auditable{

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	private String image ;
	private String tagline ;
	private String title ;
	@Column(columnDefinition="text")
	private String description;
	@Column(columnDefinition="text")
	private String content ;
	private String slug ;

	/**
	 * Tags
	 */

	@ManyToOne
    private Tags tags;

	/**
	 * Pages
	 */
	@ManyToOne
	private Pages page ;

	/**
	 * Types
	 */
	@Column(name = "Types")
	private Enum <Types> type;

	/**
	 * Entreprise
	 */
	@ManyToOne
	private Entreprise entreprise;

	/***
	 * Post List
	 */

	@OneToMany(mappedBy="post",cascade = CascadeType.ALL)
	private List<PostTranslation> postTranslations;

}
