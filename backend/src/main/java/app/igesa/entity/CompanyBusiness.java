package app.igesa.entity;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="CompanyBusiness")
public class CompanyBusiness extends Auditable<String> {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	private String description ;
	private String domainename ;

	@OneToMany(mappedBy="companyBusiness",cascade = CascadeType.ALL)
    private List<Groupe> groupe ;


    public CompanyBusiness(String description ,String domainename) {
        super();
		this.description=description;
		this.domainename=domainename;
    }
}
