package app.igesa.entity.pages;
import javax.persistence.*;
import app.igesa.entity.Auditable;
import app.igesa.entity.Entreprise;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Tarchoun Abir
 *
 **/

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper= true)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="Pages-3")
public class Page3  extends Auditable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;
    @Column(name="title")
    private String title ;
    @Column(name="description")
    private String htmlContent ;
    @ManyToOne
    private Entreprise entreprise;

}
