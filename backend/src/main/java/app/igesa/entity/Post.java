package app.igesa.entity;

import javax.persistence.*;

import app.igesa.enumerations.Types;
import app.igesa.translation.PostTranslation;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Post")
@EqualsAndHashCode(callSuper= true)
@EntityListeners(AuditingEntityListener.class)
public class Post extends Auditable<String>{

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

	private String subtitle ;
	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "tags_id")
    private Tags tags;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "page_id")
	private Pages page ;

	@Column(name = "Types")
	private Enum <Types> type;

}
