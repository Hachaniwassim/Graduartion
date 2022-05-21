package app.igesa.entity.pages;
import app.igesa.entity.Auditable;
import app.igesa.entity.Entreprise;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

/**
 *
 * @author Tarchoun Abir
 *
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
@Table(name="Pages-1")
public class Page1 extends Auditable {

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
