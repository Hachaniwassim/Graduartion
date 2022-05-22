package app.igesa.metiers;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author Tarchoun Abir
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Service
public class EntrepriseImpl implements Ientreprise {

	@Autowired
	private IentrepriseRepository entrepriseRepository;
	@Autowired
	private IauthService authService;

	private static final Logger log = LoggerFactory.getLogger(EntrepriseImpl.class);


	@Override
	public EntrepriseDTO save(EntrepriseDTO e) {
		log.debug( "<========================= ADD Entreprise ================================>");
		Entreprise saved = entrepriseRepository.save(EntrepriseDTO.toEntity(e));
		return EntrepriseDTO.fromEntity(saved);
        }

	@Override
	public Entreprise getCurrentEnterprise() {

		log.debug( "<========================= Current Entreprise ================================>");
		Long entrepriseId = authService.getIdentity().getEntrepriseId();
		if (null == entrepriseId) {
			throw new IllegalArgumentException();
		}

		return entrepriseRepository.findById(entrepriseId).orElseThrow(IllegalArgumentException::new);

	}


      @Override
      public Collection<EntrepriseDTO> view() {
		  log.debug( "<========================= Get All Entreprise ================================>");
	     return entrepriseRepository.findAll().stream()
	       .map(EntrepriseDTO::fromEntity)
	       .collect(Collectors.toList());
       }


    @Override
     public EntrepriseDTO findById(Long id) {
		log.debug( "<========================= ADD Groupe =============================>");
	    if ( id == null) {
		log.error(" Entreprise Id is NULL .. !!");
		return null ;
		}
	      return  entrepriseRepository.findById(id).map(EntrepriseDTO::fromEntity).orElseThrow(()->
	      new ResourceNotFoundException(" No Entreprise  with  Id = :: " +id+ " was founded {} ..!",
	      ErrorCode.ENTREPRISE_NOT_FOUND));
	     }

       @Override
       public void delete(Long id) {
		   log.debug( "<=========================  Delete Entreprise ===========================>");
    	   if ( id == null) {
    			log.error(" ENTREPRISE ID IS NULL ");
    		    return;
    			}
           entrepriseRepository.deleteById(id);
            }


      @Override
      public List<EntrepriseDTO> getEntrepriseByGroupe(Long id) {
		log.debug( "<=========================  Get Entreprise By Groupe  ===========================>");
		return entrepriseRepository.findEntrepriseByGroupe(id).stream()
				.map(EntrepriseDTO::fromEntity)
				.collect(Collectors.toList());
       }

	@Override
	public List<EntrepriseDTO> FindEntrepriseByCompanyname(String companyname) {
		log.debug( "<=========================  Entreprise  ===========================>");
		return entrepriseRepository.findEntrepriseByCompanyname(companyname).stream().map(EntrepriseDTO::fromEntity).collect(Collectors.toList());
	}



}