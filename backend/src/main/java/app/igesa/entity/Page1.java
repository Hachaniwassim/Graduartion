package app.igesa.entity;
import lombok.*;
import javax.persistence.*;

/**
 * @author  Tarchoun Abir
 *
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Page1")
public class Page1  extends Auditable{
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
