package app.igesa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Privacy")
@EqualsAndHashCode(callSuper= true)
@EntityListeners(AuditingEntityListener.class)
public class Privacy extends  Auditable<String> {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;

    @Column(name="title")
    private String title ;

    @Column(name="description")
    private String description ;



}
