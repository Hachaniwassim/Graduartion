package it.igesa.services.implement;
import it.igesa.enumerations.GroupStatus;
import it.igesa.services.IauthService;
import it.igesa.services.Igroupe;
import it.igesa.enumerations.ErrorCode;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.repository.IgroupeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.igesa.dto.GroupeDTO;
import it.igesa.domaine.Groupe;

/**
 *
 * @author Tarchoun Abir
 *
 */

@Service
public class GroupeImpl implements Igroupe {

	@Autowired
	private IgroupeRepository groupeRepository;
	private static final Logger log = LoggerFactory.getLogger(GroupeImpl.class);

	@Autowired
	private IauthService authService;


	@Override
	public GroupeDTO save(GroupeDTO g) {
		log.debug( "<========================= ADD Groupe ================================>");
		g.setGroupStatus(GroupStatus.PENDING);
		Groupe saved = groupeRepository.save(GroupeDTO.toEntity(g));
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

		log.debug( "<========================= Find By Id  Groupe ================================>");
		Optional<Groupe> groupe = groupeRepository.findById(id);
		GroupeDTO dto = GroupeDTO.fromEntity(groupe.get());
		return Optional.of(dto).orElseThrow(() ->
				new ResourceNotFoundException(" Aucune Entreprise avec id =" + id + " n'ete  trouver ",
						ErrorCode.GROUPE_NOT_FOUND));

	}

	@Override
	public void delete(Long id) {

		log.debug( "<========================= Delete Groupe ================================>");
		if (id == null) {
			log.error(" GROUPE ID IS NULL ");
			return;
		}
		groupeRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {

		log.debug( "<========================= Delete All Groupe ================================>");
		groupeRepository.deleteAll();
	}

	@Override
	public GroupeDTO updateSatus(Long id, GroupStatus status) {

		log.debug( "<========================= Update Status  Groupe ================================>");
		Optional<Groupe> Data = groupeRepository.findById(id);
		Groupe saved = null;
		if (Data.isPresent()) {
			Groupe groupe = Data.get();

			if (GroupStatus.ACTIVE == status) {
				groupe.setGroupStatus(GroupStatus.BLOCKED);
			}
			if (GroupStatus.BLOCKED== status) {
				groupe.setGroupStatus(GroupStatus.PENDING);
			}
			if (GroupStatus.PENDING== status) {
				groupe.setGroupStatus(GroupStatus.ACTIVE);
			}

			saved = groupeRepository.save(groupe);
		}
		return GroupeDTO.fromEntity(saved);
	}



	@Override
	public Groupe getCurrentGroup() {
		log.debug( "<========================= Get Current Groupe ================================>");
		Long groupeId = authService.getIdentity().getGroupeId();
		if (null == groupeId ) {
			throw new IllegalArgumentException();
		}
		return groupeRepository.findById(groupeId).orElseThrow(IllegalArgumentException::new);
	}

}


    
    
    
    
    
    
    
    
    
    




