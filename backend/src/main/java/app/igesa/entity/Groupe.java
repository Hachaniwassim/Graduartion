package app.igesa.entity;
import java.util.List;
import javax.persistence.*;
import app.igesa.enumerations.GroupStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Tarchoun Abir
 *
 **/
@AllArgsConstructor
@Data
@Entity
@Getter
@Setter
@Table(name="groupe")
@EntityListeners(AuditingEntityListener.class)
public class Groupe  extends Auditable{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="Name", unique = true)
    private String name;

    @Column(name = "Description")
    private String description;
    /**
     * Status active :: blocked :: pending
     */
    @Column(name = "GroupStatus")
    private GroupStatus groupStatus;

    //Enterprise
    @JsonIgnore
    @OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL)
    private List<Entreprise> entreprise;

    //Account
    @JsonIgnore
    @OneToMany(mappedBy = "groupe")
    private List<Account> accounts;

    /**
     * Activity Types
     */
    @ManyToOne
    private CompanyBusiness companyBusiness;



public Groupe(){
    super();
}
}