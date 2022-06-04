package it.igesa.domaine;

import lombok.*;

import javax.persistence.*;

/**
 * @author Tarchoun Abir
 *
 */
@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Nwes extends Auditable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;
    @Column(columnDefinition="text")
    private String title ;
    @Column(columnDefinition="text")
    private String htmlContent ;
    @Setter
    @Getter
    @ManyToOne
    private Entreprise entreprise;


}
