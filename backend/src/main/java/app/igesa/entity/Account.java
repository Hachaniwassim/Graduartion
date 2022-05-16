package app.igesa.entity;
/**
 *  @author Tarchoun Abir
 */
import app.igesa.enumerations.AccountStatus;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(	name = "accounts",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class Account extends Auditable {

    private static final long serialVersionUID = 65981149772133526L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Email
    private String email;
    private String password;
    private String matchingPassword;
    private String fiscaleCode;
    private AccountStatus accountStatus;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(fetch= FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "groupe_id",updatable = false)
    private Groupe groupe ;
    @ManyToOne(fetch= FetchType.LAZY)
    private Entreprise entreprise ;

    public Account(String username, String email, String encode, String encode1, String fiscaleCode, AccountStatus accountStatus) {
        super();
        this.username=username;
        this.email=email;
        this.password=encode;
        this.matchingPassword=encode1;
        this.fiscaleCode=fiscaleCode;
        this.accountStatus=accountStatus;
    }

}
