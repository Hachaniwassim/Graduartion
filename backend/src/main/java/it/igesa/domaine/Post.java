package it.igesa.domaine;
import javax.persistence.*;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


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
	@Column(columnDefinition="text")
	private String title ;
	@Column(columnDefinition="text")
	private String htmlContent ;



	/**
	 * Entreprise
	 */
	@Setter
	@Getter
	@ManyToOne
	private Entreprise entreprise;

	/***
	 * Post List
	 */

	//@OneToMany(mappedBy="post",cascade = CascadeType.ALL)
	//private List<PostTranslation> postTranslations;
	//private List<PostTranslation> postTranslations;


}
