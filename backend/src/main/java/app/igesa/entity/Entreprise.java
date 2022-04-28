package app.igesa.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="entreprise")
public class Entreprise  extends Auditable<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name="Email")
	private String email ;
	
	@Column(name="Phone")
	private String phone ;
	
	@Column(name="CodeFiscale")
	private String codefiscale ;

	@Column(name="Fax")
	private String fax ;

	@Column(name="Note")
	private String note ;

	@Column(name="CompanyName")
	private String companyname ;


	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="groupe_id")
	private Groupe groupe;
	
	

	
	
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
    private List<Plateforme>plateforme;

	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<ConfigGenerale>config;

	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Product>product;

}

