package app.igesa.entity;
import javax.persistence.*;

import app.igesa.entity.pages.Pages;
import app.igesa.enumerations.Types;
import app.igesa.translation.PostTranslation;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


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
	@Column(name="Image")
	private String image ;
	
	@Column(name="Tagline")
	private String tagline ;

	@OneToMany(mappedBy="post",cascade = CascadeType.ALL)
	 private List<PostTranslation> postTranslations;

	@Column(name="title")
	private String title ;

	@Column(name="description")
	private String description;

	@Column(name="content")
	private String content ;

	@Column(name="slug")
	private String slug ;


	/**
	 * Tags
	 */

	@ManyToOne
    private Tags tags;

	/**
	 * Pages
	 */
	@ManyToOne
	private Pages page ;

	/**
	 * Types
	 */
	@Column(name = "Types")
	private Enum <Types> type;

}
