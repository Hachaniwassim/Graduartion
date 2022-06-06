package it.igesa.domaine;
import it.igesa.enumerations.ContactStatus;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Objects;

/**
 *
 * @author Tarchoun Abir
 *
 **/

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="FormEntity")
public class FormEntity  extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "text")
	private String message;
	private String companyname;
	private String mobile;
	private String fax;
	@Email
	private String email;
	private String adresse;
	private String nationality;
	private String referent;

	/**
	 * status Contact
	 */
	private ContactStatus contactstatus;

	/**
	 * Product used
	 */
	@Setter
	@Getter
	@ManyToOne
	private Product product;

	/**
	 * ENTERPRISE
	 */
	@Setter
	@Getter
	@ManyToOne
	private Entreprise entreprise;

}
