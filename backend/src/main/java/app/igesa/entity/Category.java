package app.igesa.entity;

import javax.persistence.*;

import app.igesa.translation.CategoryTranslation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Category")
public class Category {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id ;

	@Column(name="Image")
	private String image ;

	private String title;
	private String description ;
	
	@Column(name="MenuImage")
	private String menuimage ;
	
	@Column(name="BannerImage")
	private String bannerimage ;
	
	@Column(name="Status")
	private boolean status ;

	@OneToMany(mappedBy="category",cascade = CascadeType.ALL)
	private List<CategoryTranslation> categoryTranslations ;
}
