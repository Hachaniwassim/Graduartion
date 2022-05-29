package app.igesa.entity;

import javax.persistence.*;

import lombok.*;
import org.springframework.boot.actuate.audit.listener.AuditListener;
import app.igesa.enumerations.LangEnum;

/**
 *
 * @author Tarchoun Abir
 *
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="Languages")
@EntityListeners(value = AuditListener.class)
public class Language extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Enumerated(EnumType.STRING)
    private String code;
    @Column(columnDefinition="TEXT")
    private String name;
    /**
     * Entreprise
     */
    @Setter
    @Getter
    @ManyToOne
    private Entreprise entreprise;

}