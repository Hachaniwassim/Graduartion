package app.igesa.entity;

import lombok.*;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class BrandLogo  extends Auditable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;
    private String image ;
    @Type(type = "org.hibernate.type.TextType")
    private String description;
    @ManyToOne
    private Entreprise entreprise;
}
