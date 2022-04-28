package app.igesa.entity;

import javax.persistence.*;

import app.igesa.enumerations.PagesTypes;
import app.igesa.enumerations.RobotsTags;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Meta")
public class Meta  extends Auditable<String>{
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name="MetaTitle")
	private String metatitle ;
	
	@Column(name="MetaDescription")
	private String metadescription ;
	
	@Column(name="UrlKey")
	private String  urlkey;
	
	@Column(name="Metakey")
	private String metakey ;
	//private Post post;


	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product ;
	private PagesTypes pagesTypes;
	private RobotsTags robotsTags;
}
