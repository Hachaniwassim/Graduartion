package app.igesa.metiers;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.igesa.dto.EntrepriseDTO;
import app.igesa.entity.Entreprise;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.repository.IentrepriseRepository;
import app.igesa.repository.IgroupeRepository;
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
 @Autowired
 private AccountImp authService ;

private static final Logger log = LoggerFactory.getLogger(EntrepriseImpl.class);



	@Override
      public EntrepriseDTO save(EntrepriseDTO e) {

         Entreprise saved =entrepriseRepository.save(EntrepriseDTO.toEntity(e));
	     return EntrepriseDTO.fromEntity(saved);


              }

	@Override
	public Entreprise getCurrentEnterprise( ) {
		Long entrepriseId = authService.getIdentity().getEntrepriseId();
		if (null == entrepriseId) {
			throw new IllegalArgumentException();
		}
		return entrepriseRepository.findById(entrepriseId).orElseThrow(IllegalArgumentException::new);
	}



      @Override
      public Collection<EntrepriseDTO> view() {
	     return entrepriseRepository.findAll().stream()
	       .map(EntrepriseDTO::fromEntity)
	       .collect(Collectors.toList());
       }


    @Override
     public EntrepriseDTO findById(Long entrepriseId) {
	    if ( entrepriseId == null) {
		log.error(" Entreprise Id is NULL .. !!");
		return null ;
		}
	    
	  return  entrepriseRepository.findById(entrepriseId).map(EntrepriseDTO::fromEntity).orElseThrow(()->
	  new ResourceNotFoundException(" No Entreprise  with  Id = :: " +entrepriseId+ " was founded {} ..!",
	  ErrorCode.ENTREPRISE_NOT_FOUND));
	  
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



}