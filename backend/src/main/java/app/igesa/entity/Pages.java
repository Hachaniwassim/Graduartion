package app.igesa.entity;

import javax.persistence.*;

import app.igesa.enumerations.PagesTypes;
import app.igesa.translation.ConfigurationTranslation;
import app.igesa.translation.PagesTranslations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Pages")
public class Pages extends Auditable<String>{
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name="Published")
	private boolean published ;
    @Column(name="pageTypes")
	private PagesTypes pagetype;

	private String title ;

	private String description ;
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "entreprise_id")
	private Entreprise entreprise;

	@OneToMany(mappedBy="pages",cascade = CascadeType.ALL)
	private List<PagesTranslations> pagesTranslations;
}
