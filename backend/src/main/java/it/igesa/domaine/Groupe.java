package it.igesa.domaine;
import java.util.List;
import javax.persistence.*;
import it.igesa.enumerations.GroupStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;
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
    @Type(type = "org.hibernate.type.TextType")
    private String description;
    private String maxOperateur;
    /**
     * Status active :: blocked :: pending
     */
    private GroupStatus groupStatus;

    /**
     * Entreprise
     */
    @JsonIgnore
    @OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL)
    private List<Entreprise> entreprises;

    /**
     * Account
     */
    @JsonIgnore
    @OneToMany(mappedBy = "groupe")
    private List<Account> accounts;




public Groupe(){
    super();
}
}