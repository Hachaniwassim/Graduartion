package app.igesa.entity;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

/**
 *
 * @author Tarchoun Abir
 *
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="CompanyBusiness",
		uniqueConstraints = {
		@UniqueConstraint(columnNames = "domainename")}
)
public class CompanyBusiness extends Auditable {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	@Column(columnDefinition="text")
	private String description ;
	@Type(type = "org.hibernate.type.TextType")
	private String domainename ;
	/**
	 *
	 * Entreprise List
	 *
	 */
	@OneToMany(mappedBy="companyBusiness",cascade = CascadeType.ALL)
    private List<Entreprise> entreprises ;


    public CompanyBusiness(String description ,String domainename) {
        super();
		this.description=description;
		this.domainename=domainename;
    }
}
