package app.igesa.entity;

import javax.persistence.*;

import app.igesa.translation.PostTranslation;
import app.igesa.translation.ProductTranslation;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper= true)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table (name="Product")
public class Product extends Auditable<String>{
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	private String title ;

	private String detailimage ;

	private String note ;

	private String name ;
	@Column(name="Image")
	private String image ;
	
	@Column(name="ConsultatioNumber")
	private  int consultationNumber ;

	@ManyToOne(fetch= FetchType.LAZY, cascade = { CascadeType.MERGE,CascadeType.DETACH,CascadeType.REMOVE,CascadeType.REFRESH})
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;



	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;


	@OneToMany(mappedBy="product",cascade = CascadeType.ALL)
	private List<ProductTranslation> productTranslations;




	@OneToMany(mappedBy="product",cascade = CascadeType.ALL)
	private List<Meta> metas;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "tags_id")
	private Tags tags;

}
