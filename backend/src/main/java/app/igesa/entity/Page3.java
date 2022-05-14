package app.igesa.entity;

import javax.persistence.*;

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
@Table(name="Page1")
@EqualsAndHashCode(callSuper= true)
@EntityListeners(AuditingEntityListener.class)
public class Page3  extends Auditable{
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
