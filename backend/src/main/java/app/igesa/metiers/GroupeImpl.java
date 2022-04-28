package app.igesa.metiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import app.igesa.dto.CompanyBusinessDTO;
import app.igesa.entity.CompanyBusiness;
import app.igesa.repository.IcomapnybusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.igesa.dto.GroupeDTO;
import app.igesa.entity.Groupe;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.repository.IgroupeRepository;


@Service
public class GroupeImpl implements Igroupe {

	@Autowired
	private IgroupeRepository groupeRepository;
    private static final Logger log = LoggerFactory.getLogger(GroupeImpl.class);
	@Autowired
	private IcomapnybusRepository icompanyBusinessRepository;


	@Override
	public GroupeDTO save(GroupeDTO g) {
		/*List<String> errors = GroupeValidator.validate(g);
		if(!errors.isEmpty()) {
			log.error("Groupe not valid !", g);
			throw new InvalideEntityException("Groupe not valid !", ErrorCode.GROUPE_NOT_VALID, errors);
		}*/
		Optional<CompanyBusiness> company = icompanyBusinessRepository.findById(g.getCompanyBusiness().getId());

		if( company == null) {

			log.warn("Groupe with id =  was not found  in the database", g.getCompanyBusiness().getId());
			throw new  ResourceNotFoundException("Company not Found with Id  = " + g.getCompanyBusiness().getId()+ " In the data base" ,ErrorCode.COMPANYBUSSINESS_NOT_FOUND);
		}

		if(company.isPresent()) {
			g.setCompanyBusiness(CompanyBusinessDTO.fromEntity(company.get()));
		}

		Groupe saved =groupeRepository.save(GroupeDTO.toEntity(g));
		return GroupeDTO.fromEntity(saved);

	}
	@Override
	public Collection<GroupeDTO> view() {
	     return groupeRepository.findAll().stream()
	  	       .map(GroupeDTO::fromEntity)
	  	       .collect(Collectors.toList());
	         }

	
	@Override
	public GroupeDTO findById(Long id) {
		if ( id == null) {
			log.error(" GROUPE ID IS NULL ");
			return null ;
			}
		  Optional<Groupe> groupe = groupeRepository.findById(id);
		  GroupeDTO dto =GroupeDTO.fromEntity(groupe.get());
		  return Optional.of(dto).orElseThrow(()->
		  new ResourceNotFoundException(" Aucune Entreprise avec id =" +id+ " n'ete  trouver ",
		  ErrorCode.GROUPE_NOT_FOUND));
		
	}
	@Override
	public void delete(Long id) {
		   if ( id == null) {
   			log.error(" GROUPE ID IS NULL ");
   		    return;
   			}

		   groupeRepository.deleteById(id);
           }

	@Override
	public GroupeDTO findAllById(Long id) {
		if ( id == null) {
			log.error(" GROUPE ID IS NULL ");
			return null ;
		}
		Optional<Groupe> groupe = groupeRepository.findById(id);
		GroupeDTO dto =GroupeDTO.fromEntity(groupe.get());
		return Optional.of(dto).orElseThrow(()->
				new ResourceNotFoundException(" Aucune Entreprise avec id =" +id+ " n'ete  trouver ",
						ErrorCode.GROUPE_NOT_FOUND));

	}

	@Override
	public void deleteAll() {
		groupeRepository.deleteAll();
	}
}



    
    
    
    
    
    
    
    
    
    
    
    
	/*@Override
	public Groupe save(Groupe g) { 
		log.debug("Saving Groupe {}...", g);
		g=groupeRepository.save(g);
        log.debug("Groupe saved Successfully :: {}!", g);
        return g ;
	}

	@Override
	public Collection<Groupe> view() {
		log.debug("Finding Groupe {}...");
		Collection<Groupe> g = groupeRepository.findAll();
		log.debug("  finded all Groupe :: {} ",g);
		return g ;
	}

	@Override
	public Optional<Groupe> findById(Long id) {
		log.debug("Finding Groupe By id {}...",id);
		Optional<Groupe> g= groupeRepository.findById(id);
		log.debug("Finded all Groupe By id :: {} !",id);
		return g ;
	}

	@Override
	public void delete(Long id) {
		log.debug("Deleting Groupe By id {}...",id);
		groupeRepository.deleteById(id);
        log.debug(" deleted Successfully :: {} !",id);
		
	}

	@Override
	public Groupe update(Groupe g, Long id) {
		 log.debug("Updating Groupe {}...");
		 Groupe G = groupeRepository.findById(id).orElseThrow(() -> new RuntimeException("Entreprise not found with  id : : " +id));
	     G.setActive(g.isActive());
	     G.setConfirmed(g.isConfirmed());
	     G.setDeleted(g.isDeleted());
	     G.setDescription(g.getDescription());
	     G.setName(g.getName());
	     G.setEntreprises(g.getEntreprises());
	     final Groupe   updategroupe  = groupeRepository.save(G);
	     log.debug("Groupe Updated  successfully{}...", updategroupe );
	     return updategroupe ;
	}

	@Override
	public Groupe FindByName(String name) {
	     log.debug("Finding all Demo by Name {} ...", name);
	     return groupeRepository.findByName(name);
		}*/
    
    



