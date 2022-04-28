package app.igesa.metiers;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import app.igesa.dto.CompanyBusinessDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.igesa.dto.EntrepriseDTO;
import app.igesa.dto.GroupeDTO;
import app.igesa.entity.Entreprise;
import app.igesa.entity.Groupe;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.repository.IentrepriseRepository;
import app.igesa.repository.IgroupeRepository;
import app.igesa.validators.EntrepriseValidator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Service
public class EntrepriseImpl implements Ientreprise {
	
@Autowired
 private IentrepriseRepository entrepriseRepository ;
@Autowired
private IgroupeRepository groupeRepository;

private static final Logger log = LoggerFactory.getLogger(EntrepriseImpl.class);



	@Override
      public EntrepriseDTO save(EntrepriseDTO e) {
	   List<String> errors = EntrepriseValidator.validateEntreprise(e);
	   if (e.getId()!= null) {
		   
	   }
	    if(!errors.isEmpty()) {
		log.error("Entreprise not valid !" ,e);
		throw new InvalideEntityException("Entreprise not valid !",ErrorCode.ENTREPRISE_NOT_VALID,errors);}
	    
	    Optional<Groupe> groupe = groupeRepository.findById(e.getGroupe().getId());

	    if( groupe == null) {
	    	
	    log.warn("Groupe with id =  was not found  in the database", e.getGroupe().getId());
	    throw new  ResourceNotFoundException("Groupe not Found with Id  = " + e.getGroupe().getId()+ " In the data base" ,ErrorCode.GROUPE_NOT_FOUND);
	    }

	    if(groupe.isPresent()) {
	    	e.setGroupe(GroupeDTO.fromEntity(groupe.get()));
	    }
	    
         Entreprise saved =entrepriseRepository.save(EntrepriseDTO.toEntity(e));
	     return EntrepriseDTO.fromEntity(saved);

              }


      @Override
      public Collection<EntrepriseDTO> view() {
	     return entrepriseRepository.findAll().stream()
	       .map(EntrepriseDTO::fromEntity)
	       .collect(Collectors.toList());
       }


    @Override
     public Optional<EntrepriseDTO> findById(Long id) {
	    if ( id == null) {
		log.error(" Entreprise Id is NULL .. !!");
		return null ;
		}
	    
	  return  Optional.of(entrepriseRepository.findById(id).map(EntrepriseDTO::fromEntity).orElseThrow(()->
	  new ResourceNotFoundException(" No Entreprise  with  Id = :: " +id+ " was founded {} ..!",
	  ErrorCode.ENTREPRISE_NOT_FOUND)));
	  
     }

       @Override
       public void delete(Long id) {
    	   if ( id == null) {
    			log.error(" ENTREPRISE ID IS NULL ");
    		    return;
    			}
           entrepriseRepository.deleteById(id);
            }


      @Override
      public List<EntrepriseDTO> getEntrepriseByGroupe(Long id) {
		return entrepriseRepository.findEntrepriseByGroupe(id).stream().map(EntrepriseDTO::fromEntity).collect(Collectors.toList());
       }

	@Override
	public List<EntrepriseDTO> FindEntrepriseByCompanyname(String companyname) {
		return entrepriseRepository.findEntrepriseByCompanyname(companyname).stream().map(EntrepriseDTO::fromEntity).collect(Collectors.toList());
	}

	@Override
	public Thread getCurrentEnterprise() {
		return (Thread) entrepriseRepository.findAll();
	}


}