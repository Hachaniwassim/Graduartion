package it.igesa.domaine;
import javax.persistence.*;
import javax.validation.constraints.Email;
import it.igesa.translation.ConfigurationTranslation;
import lombok.*;

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
@Setter
@Getter
@Table(name =" Configurations")
public class ConfigGenerale extends Auditable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	private String facebook ;
	private String twitter ;
	private String youtube ;
	private  String adresse;
	@Email
	private String email ;
	private String phone ;
	private String fax ;
	@Column(columnDefinition="text")
	private String copyright ;
	@Column(columnDefinition="text")
	private String newslettertitle;
	@Column(columnDefinition="text")
	private String newslettersubtitle ;

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
