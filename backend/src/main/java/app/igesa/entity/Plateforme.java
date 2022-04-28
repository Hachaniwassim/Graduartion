package app.igesa.entity;


import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper= true)
@Entity
@Table(name="plateform")
@EntityListeners(AuditingEntityListener.class)
public class Plateforme extends Auditable<String>{

	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ; 
	
	@Column(name="Email")
	private String email ; 
	
	@Column(name="phone")
	private String phone ;
	
	@Column(name="Adresse")
	private String adresse ;
	
	@Column(name="Published")
	private Boolean published;
	
/*-------------------------------Relations--------------------------------------*/

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="entreprise_id")
	private Entreprise entreprise;

/*---------------------------------------------------------------------------------*/


}
