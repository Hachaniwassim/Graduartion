package it.igesa.domaine;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;

import it.igesa.domaine.pages.Page1;
import it.igesa.domaine.pages.Page2;
import it.igesa.domaine.pages.Page3;
import it.igesa.domaine.pages.Pages;
import it.igesa.domaine.seo.Seo;
import it.igesa.domaine.siteinfo.Plateforme;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;


/**
 *
 * @author Tarchoun Abir
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="entreprise")
public class Entreprise  extends Auditable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	@Email
	private String email ;
	private String phone ;
	private String codefiscale ;
	private String fax ;
	@Type(type = "org.hibernate.type.TextType")
	private String note ;
	@Column(name="CompanyName", unique = true)
	private String companyname ;
	private String websiteUrl;
	private String adresse;
	private String city ;
	private String street;
	private String refrente;
	@Column(name="CodeBank", unique = true)
	private String CodeBank;
	@Column(name="siretNumber", unique = true)
	private String siretNumber;

	/**
	 * Groupe
	 */

	@ManyToOne
	private Groupe groupe;


	/**
	 * Activity Types
	 */
	@ManyToOne
	private CompanyBusiness companyBusiness;


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
	private List<Privacy> privacys;
	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Cookies> cookies;
	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<FormEntity> formEntity;
	@JsonIgnore
	@OneToMany(mappedBy="enterprise",cascade = CascadeType.ALL)
	private List<Category> categorys= new ArrayList<>();
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
	@OneToMany(mappedBy="entreprise",cascade = {CascadeType.REFRESH, CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST})
	private List<Product> products= new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Tags> tags= new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Nwes> news = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Liens> liens= new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<Language> languages= new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List< Seo> seos= new ArrayList<>();





	@JsonIgnore
	@OneToMany(mappedBy="entreprise",cascade = CascadeType.ALL)
	private List<PostRevendeur> postRevendeurs= new ArrayList<>();






}

