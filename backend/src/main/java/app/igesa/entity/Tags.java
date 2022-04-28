package app.igesa.entity;

import javax.persistence.*;

import app.igesa.translation.TagsTranslation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Tags")
public class Tags extends Auditable<String> {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	private String name ;

	private String description ;

	@OneToMany(mappedBy="tags",cascade = CascadeType.ALL)
	private List<Post>posts ;

	@OneToMany(mappedBy="tags",cascade = CascadeType.ALL)
	private List<Product>products;

	@OneToMany(mappedBy="tags",cascade = CascadeType.ALL)
	private List<TagsTranslation>tagsTranslations;
}
