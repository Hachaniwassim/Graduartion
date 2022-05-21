package app.igesa.entity;
import java.util.List;
import javax.persistence.*;

import app.igesa.entity.siteinfo.Plateforme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author Tarchoun Abir
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="entreprise")
public class Entreprise  extends Auditable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	private String email ;
	private String phone ;
	private String codefiscale ;
	private String fax ;
	private String note ;
	@Column(name="CompanyName", unique = true,nullable = false)
	private String companyname ;

    /**
	 * Groupe
     */

	@ManyToOne
	private Groupe groupe;
	/**
	 * Lists
	 */

	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
    private List<Plateforme>plateforme;

	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL )
	private List<ConfigGenerale>config;

	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Product>product;

	//@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	//private List<Privacy>privacies;

	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Cookies> cookies;

	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<FormEntity> formEntities;


}

