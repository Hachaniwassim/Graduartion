package app.igesa.metiers;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import app.igesa.dto.EntrepriseDTO;
import app.igesa.dto.PlateformeDTO;
import app.igesa.entity.Entreprise;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.repository.IentrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.igesa.entity.Plateforme;
import app.igesa.repository.IplateformeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PlateformeImpl implements Iplateforme {

	private static final Logger log = LoggerFactory.getLogger(PlateformeImpl.class);

	 @Autowired
	 IplateformeRepository plateformeRepository;
	 @Autowired
	IentrepriseRepository entrepriseRepository;
	@Override
	public PlateformeDTO save(PlateformeDTO p) {
		/*List<String> errors = PlateformeValidator.validate(p);

		if(!errors.isEmpty()) {
			log.error("Plateforme not valid !" ,p);
			throw new InvalideEntityException("Plateforme not valid !", ErrorCode.PLATEFORME_NOT_VALID,errors);}*/

		// if i find a group with the privilege id  than its OK  else throw exception

		Optional<Entreprise> entreprise= entrepriseRepository.findById(p.getEntreprise().getId());
	    if( entreprise.get().getId() == null) {

	    log.warn("Entreprise with id =  was not found  in the database", p.getEntreprise().getId());
	    throw new  ResourceNotFoundException("Groupe not Found with Id  = " +p.getEntreprise().getId()+ " In the data base" ,ErrorCode.ENTREPRISE_NOT_FOUND);
	    }
		if(entreprise.isPresent()) {
			p.setEntreprise(EntrepriseDTO.fromEntity(entreprise.get()));
		}
		Plateforme saved =plateformeRepository.save(PlateformeDTO.toEntity(p));
		return PlateformeDTO.fromEntity(saved);

	}

	@Override
	public Collection<PlateformeDTO> view() {
		return plateformeRepository.findAll().stream()
				.map(PlateformeDTO::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<PlateformeDTO> findById(Long id) {
		if ( id == null) {
			log.error(" Plateforme Id is NULL .. !!");
			return null ;
		}

		return  Optional.of(plateformeRepository.findById(id).map(PlateformeDTO::fromEntity).orElseThrow(()->
				new ResourceNotFoundException(" No Plateforme  with  Id = :: " +id+ " was founded {} ..!",
						ErrorCode.PLATEFORME_NOT_FOUND)));
	}

	@Override
	public void delete(Long id) {
		if ( id == null) {
			log.error(" PLATEFORME ID IS NULL ");
			return;
		}
		plateformeRepository.deleteById(id);;
			}

	@Override
	public PlateformeDTO update( Long id,PlateformeDTO p) {

		    return null ;
	}

}
