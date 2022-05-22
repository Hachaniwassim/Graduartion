package app.igesa.entity;
import javax.persistence.*;
import javax.validation.constraints.Email;
import app.igesa.translation.ConfigurationTranslation;
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
 **/

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name =" Configuration")
@EqualsAndHashCode(callSuper= true)
@EntityListeners(AuditingEntityListener.class)
public class ConfigGenerale extends Auditable{

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	private String facebook ;
	private String twitter ;
	private String youtube ;
	private String image ;
	private String favicon ;
	private String adresse ;
	@Email
	private String email ;
	private String phone ;
	private String fax ;
	private String copyright ;
	private String title ;
	private String newslettertitle;
	private String newslettersubtitle ;
	private String tagline ;

	/**
	 *
	 * Entreprise
	 *
	 */
	@ManyToOne
	private Entreprise entreprise;
	/**
	 *
	 * Translation
	 *
	 */

	@OneToMany(mappedBy="configGenerale",cascade = CascadeType.ALL)
	private List<ConfigurationTranslation> configurationTranslations;
}
