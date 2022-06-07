package it.igesa.domaine;
import it.igesa.enumerations.RevendeursStatus;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.Email;
/**
 *
 * @author Tarchoun Abir
 *
 **/

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PostRevendeur extends Auditable{


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text")
    private String message;
    private String companyname;
    private String mobile;
    private String cap;
    @Email
    private String email;
    private String adresse;
    private String nationality;
    private String referent;

    /**
     * status Revendeurs
     */
    private RevendeursStatus revendeursStatus;

    /**
     * Product used
     */
    @Setter
    @Getter
    @ManyToOne
    private Product product;

    /**
     * ENTERPRISE
     */

    @ManyToOne(fetch = FetchType.LAZY)
    private Entreprise entreprise;

}
