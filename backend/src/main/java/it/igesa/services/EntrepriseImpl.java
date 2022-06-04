package it.igesa.services;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import it.igesa.domaine.Entreprise;
import it.igesa.enumerations.ErrorCode;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.repository.IentrepriseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.igesa.dto.EntrepriseDTO;
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
		log.debug( "<========================= ADD Enterprise ==========================>");
		Entreprise saved = entrepriseRepository.save(EntrepriseDTO.toEntity(e));
		//entrepriseRepository.count();
		return EntrepriseDTO.fromEntity(saved);
        }

	@Override
	public Entreprise getCurrentEnterprise() {

		log.debug( "<========================= Current Enterprise ============================>");
		Long entrepriseId = authService.getIdentity().getGroupeId();
		if (null == entrepriseId) {
			throw new IllegalArgumentException();
		}

		return entrepriseRepository.findById(entrepriseId).orElseThrow(IllegalArgumentException::new);

	}

	@Override
	public List<EntrepriseDTO> getEntrepriseByGroupe(Long id) {
		log.debug( "<=========================  Get Enterprise By Group  ===========================>");
		return entrepriseRepository.findEntrepriseByGroupe(id).stream()
				.map(EntrepriseDTO::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
      public Collection<EntrepriseDTO> view() {
		  log.debug( "<========================= Get All Enterprise ================================>");
	     return entrepriseRepository.findAll().stream()
	       .map(EntrepriseDTO::fromEntity)
	       .collect(Collectors.toList());
       }


    @Override
     public EntrepriseDTO findById(Long id) {
		log.debug( "<========================= ADD Group =============================>");
	    if ( id == null) {
		log.error(" Enterprise Id is NULL .. !!");
		return null ;
		}
	      return  entrepriseRepository.findById(id).map(EntrepriseDTO::fromEntity).orElseThrow(()->
	      new ResourceNotFoundException(" No Enterprise  with  Id = :: " +id+ " was founded {} ..!",
	      ErrorCode.ENTREPRISE_NOT_FOUND));
	     }

       @Override
       public void delete(Long id) {
		   log.debug( "<=========================  Delete Enterprise ===========================>");
    	   if ( id == null) {
    			log.error(" ENTERPRISE ID IS NULL ");
    		    return;
    			}
           entrepriseRepository.deleteById(id);
            }




	@Override
	public List<EntrepriseDTO> FindEntrepriseByCompanyname(String companyname) {
		log.debug( "<=========================  Enterprise  ===========================>");
		return entrepriseRepository.findEntrepriseByCompanyname(companyname).stream().map(EntrepriseDTO::fromEntity).collect(Collectors.toList());
	}



}