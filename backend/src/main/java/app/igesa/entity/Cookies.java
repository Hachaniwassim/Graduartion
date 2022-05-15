package app.igesa.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;


/**
 * @author Tarchoun Abir
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Cookies")
@EqualsAndHashCode(callSuper= true)
@EntityListeners(AuditingEntityListener.class)
public class Cookies extends Auditable {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id ;
        @Column(name="title")
        private String title ;
        @Column(name="description")
        private String htmlContent ;
        //private Boolean active ;
        @ManyToOne
        private Entreprise entreprise;

    }


