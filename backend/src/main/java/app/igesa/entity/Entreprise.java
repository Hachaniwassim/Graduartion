package app.igesa.entity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import app.igesa.entity.pages.Pages;
import app.igesa.entity.siteinfo.Plateforme;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	 *
	 */
    @JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
    private List<Plateforme>plateformes;
	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL )
	private List<ConfigGenerale>configs;
	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Product>products;
	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	 private List<Privacy> privacys;
	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Cookies> cookies;
	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<FormEntity> formEntity;
	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Category> category = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Post> post= new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Page1> page1s= new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Page2> page2s= new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Page3> page3s= new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Pages> pages= new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Account> accounts= new ArrayList<>();




}

