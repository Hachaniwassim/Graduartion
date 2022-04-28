package app.igesa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity@Table(	name = "accounts",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class Account {

    private static final long serialVersionUID = 65981149772133526L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @Email
    private String email;
    @NotBlank
    private String password;

    @NotBlank
    private String matchingPassword;

    @NotBlank
    private String fiscaleCode ;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


 public Account( String username, String email, String password, String matchingPassword, String fiscaleCode){
     super();
     this.username=username;
     this.email=email;
     this.password=password;
     this.matchingPassword=matchingPassword;
     this.fiscaleCode=fiscaleCode;
 }


    public Account( String username, String email, String password, String matchingPassword,Set<Role> roles , String fiscaleCode){
        super();
        this.username=username;
        this.email=email;
        this.password=password;
        this.matchingPassword=matchingPassword;
        this.fiscaleCode=fiscaleCode;
        this.roles=roles;
    }



    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "groupe_id")
    private Groupe groupe ;


}
