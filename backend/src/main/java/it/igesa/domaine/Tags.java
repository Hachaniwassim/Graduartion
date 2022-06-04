package it.igesa.domaine;

import javax.persistence.*;
import it.igesa.translation.TagsTranslation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 *
 * @author Tarchoun Abir
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Tags")
public class Tags extends Auditable {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	@Column(columnDefinition="text")
	private String description ;
	/**
	 * Entreprise
	 */
       @ManyToOne
	   private Entreprise entreprise ;

	/**
	 *
	 * translation
	 */
    @JsonIgnore
	@OneToMany(mappedBy="tags",cascade = CascadeType.ALL)
	private List<TagsTranslation>tagsTranslations;
}
