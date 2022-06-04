package it.igesa.domaine.siteinfo;

import javax.persistence.*;

import it.igesa.domaine.Auditable;
import it.igesa.domaine.Entreprise;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @author Tarchoun Abir
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper= true)
@Entity
@Table(name="plateform")
@EntityListeners(AuditingEntityListener.class)
public class Plateforme extends Auditable {

	
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
	@ManyToOne
	private Entreprise entreprise;



}
