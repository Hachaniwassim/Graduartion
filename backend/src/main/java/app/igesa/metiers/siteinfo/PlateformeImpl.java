package app.igesa.metiers.siteinfo;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import app.igesa.dto.PlateformeDTO;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.metiers.Ientreprise;
import app.igesa.metiers.Iplateforme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.igesa.entity.siteinfo.Plateforme;
import app.igesa.repository.siteinfo.IplateformeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PlateformeImpl implements Iplateforme {

	private static final Logger log = LoggerFactory.getLogger(PlateformeImpl.class);

	 @Autowired
	 IplateformeRepository plateformeRepository;
	 @Autowired
	 Ientreprise entrepriseService;

	@Override
	public PlateformeDTO save(PlateformeDTO p) {

		log.debug( "<=========================  save site info  ===========================>");

		Plateforme saved = plateformeRepository.save(PlateformeDTO.toEntity(p));
		return PlateformeDTO.fromEntity(saved);

	}

	@Override
	public Collection<PlateformeDTO> getCurrentSiteInfo(Long id_entreprise){

		log.debug( "<=========================  Get current site info  ===========================>");
		return plateformeRepository.findByEntrepriseId(id_entreprise).stream()
				.map(PlateformeDTO::fromEntity)
				.collect(Collectors.toList());
	}
	@Override
	public Optional<PlateformeDTO> findById(Long id) {

		log.debug( "<=========================  Get  By id ===========================>");
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

		log.debug( "<=========================  delete site ===========================>");
		if ( id == null) {
			log.error(" PLATEFORME ID IS NULL ");
			return;
		}
		plateformeRepository.deleteById(id);;
			}

	@Override
	public PlateformeDTO updateSiteInfo( PlateformeDTO p) {

		log.debug( "<=========================  update site Info  ===========================>");
		Plateforme saved = plateformeRepository.save(PlateformeDTO.toEntity(p));
		return PlateformeDTO.fromEntity(saved);
	}


}
