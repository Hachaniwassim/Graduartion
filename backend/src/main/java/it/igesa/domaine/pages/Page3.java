package it.igesa.domaine.pages;
import it.igesa.domaine.Auditable;
import it.igesa.domaine.Entreprise;
import lombok.*;
import javax.persistence.*;

/***
 * @author  Tarchoun Abir
 *
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Content")
@Table(name = "Content")
public class Page3  extends Auditable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;
    @Column(columnDefinition="text")
    private String title ;
    @Column(columnDefinition="text")
    private String htmlContent ;
    @ManyToOne
    private Entreprise entreprise;

}
