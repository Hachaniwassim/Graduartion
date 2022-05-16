package app.igesa.metiers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import app.igesa.dto.CompanyBusinessDTO;
import app.igesa.entity.CompanyBusiness;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.repository.IgroupeRepository;
import app.igesa.validators.CompanyBusinessValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.igesa.repository.IcomapnybusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CompanyBusinessImpl implements IcompanyBusiness {

	private static final Logger log = LoggerFactory.getLogger(CompanyBusinessImpl.class);

	@Autowired
	private IcomapnybusRepository companybusinessRepository;
	private IgroupeRepository igroupeRepository;


	@Override
	public CompanyBusinessDTO save(CompanyBusinessDTO c) {
		List<String> errors = CompanyBusinessValidator.validate(c);
		log.debug("HTTP POST {} ..", c);
		if (!errors.isEmpty()) {
			log.error("CompanyBussiness not valid !", c);
			throw new InvalideEntityException("Company Business not valid!", ErrorCode.COMPANYBUSSNESS_NOT_VALID, errors);
		}


		CompanyBusiness saved = companybusinessRepository.save(CompanyBusinessDTO.toEntity(c));
		return CompanyBusinessDTO.fromEntity(saved);

	}


	@Override
	public Collection<CompanyBusinessDTO> view() {
		log.debug("HTTP GET ALL {} ..");
		return companybusinessRepository.findAll().stream()
				.map(CompanyBusinessDTO::fromEntity)
				.collect(Collectors.toList());
	}


	@Override
	public CompanyBusinessDTO findById(Long id) {
		log.debug("HTTP GET BY ID {} ..", id);
		if (id == null) {
			log.error(" Company busniss Id is NULL .. !!");
			return null;
		}

		return companybusinessRepository.findById(id).map(CompanyBusinessDTO::fromEntity).orElseThrow(() ->
				new ResourceNotFoundException(" No Company Business  with  Id = :: " + id + " was founded {} ..!",
						ErrorCode.COMPANYBUSSINESS_NOT_FOUND));

	}


	@Override
	public void delete(Long id) {
		log.debug("HTTP DELETE BY ID {} ..", id);
		if (id == null) {
			log.error(" ENTREPRISE ID IS NULL ");
			return;
		}
		//Optional<Groupe> groupe = igroupeRepository.findById(id);
		//if (groupe.get().getId() != null) {
		//	throw new InvalideEntityException("impossible de supprimer une company déja utilisé par un groupe ", ErrorCode.COMPANY_ALREADY_IN_USE);
		//}

		companybusinessRepository.deleteById(id);
	}

	@Override
	public List<CompanyBusinessDTO> FindCompanyBusinessByDescription(String description) {
		return companybusinessRepository.findCompanyBusinessesByDescription(description).stream().map(CompanyBusinessDTO::fromEntity).collect(Collectors.toList());
	}


	/*@Override
	public Page<CompanyBusinessDTO> FindCompanyBusinessByDescription(String description, int page, int size) {
		return companybusinessRepository.findCompanyBusinessByDescription(description, PageRequest.of(page, size)).map(CompanyBusinessDTO::fromEntity);

*/



}





