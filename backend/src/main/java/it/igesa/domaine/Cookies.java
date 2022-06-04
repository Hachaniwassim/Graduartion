package it.igesa.domaine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;


/**
 *
 * @author Tarchoun Abir
 *
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Cookies")
@EqualsAndHashCode(callSuper= true)
@EntityListeners(AuditingEntityListener.class)
public class Cookies extends Auditable {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id ;
        @Column(columnDefinition="text")
        private String title ;
        @Column(columnDefinition="text")
        private String htmlContent ;
        /**
         * Entreprise
         */
        @ManyToOne
        private Entreprise entreprise;

    }


