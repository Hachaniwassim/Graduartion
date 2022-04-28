package app.igesa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.boot.actuate.audit.listener.AuditListener;
import app.igesa.enumerations.LangEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="language")
@EntityListeners(value = AuditListener.class)
public class Language extends Auditable <String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@Column(unique = true, nullable = false)
   // private String uuid;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LangEnum lang;
    @Column(columnDefinition="TEXT")
    private String name;
    private String image;
    private boolean active;
    
}