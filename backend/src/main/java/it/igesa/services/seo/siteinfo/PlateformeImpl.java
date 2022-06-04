package it.igesa.services.seo.siteinfo;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import it.igesa.dto.PlateformeDTO;
import it.igesa.enumerations.ErrorCode;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.services.Ientreprise;
import it.igesa.services.Iplateforme;
import it.igesa.repository.siteinfo.IplateformeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.igesa.domaine.siteinfo.Plateforme;
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
