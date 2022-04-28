package app.igesa.entity;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@AllArgsConstructor
@Data
@Entity
@Table(name="groupe")
@EntityListeners(AuditingEntityListener.class)
public class Groupe  extends Auditable<String> {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Active")
    private boolean active;

    @Column(name = "Confirmed")
    private boolean confirmed;

    @Column(name = "Deleted")
    private boolean deleted;

    /*--------------------------------------Relations----------------------------------*/

    //Enterprise
    @JsonIgnore
    @OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL)
    private List<Entreprise> entreprise;

    //Account
    @JsonIgnore
    @OneToMany(mappedBy = "groupe")
    private List<Account> accounts;



    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "company_business_id")
    private CompanyBusiness companyBusiness;

    public Groupe( String name, String description, boolean active, boolean confirmed, boolean deleted) {

        this.name = name;
        this.description = description;
        this.active = active;
        this.confirmed = confirmed;
        this.deleted = deleted;
    }

    public Groupe(boolean active){
        this.active=active;
    }
public Groupe(){
    super();
}
}