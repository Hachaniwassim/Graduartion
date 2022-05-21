package app.igesa.entity;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private String domainename ;
	/**
	 *
	 * Groupe List
	 *
	 */
	@OneToMany(mappedBy="companyBusiness",cascade = CascadeType.ALL)
    private List<Groupe> groupe ;


    public CompanyBusiness(String description ,String domainename) {
        super();
		this.description=description;
		this.domainename=domainename;
    }
}
