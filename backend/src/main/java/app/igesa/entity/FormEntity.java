package app.igesa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name="Contact")
@EqualsAndHashCode(callSuper= true)
@EntityListeners(AuditingEntityListener.class)
public class FormEntity  extends Auditable<String>{
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name="Name")
	private String name ; 
	
	@Column(name="CompanyName")
	private String companyname;
	
	@Column(name="Mobile")
	private String mobile ;
	
	@Column(name="Fax")
	private String fax ;
	
	@Column(name="Email")
	private String email ;
	
	@Column(name="Adresse")
	private String adresse ;
	
	@Column(name="Nationality")
	private String nationality ;
	
	@Column(name="Refrent")
	private String referent ;
	//private Product softwareused ;
	
	@Column(name="Status")
	private boolean status ;
	

}
