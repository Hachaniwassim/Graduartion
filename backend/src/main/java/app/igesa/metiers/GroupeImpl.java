package app.igesa.metiers;
import app.igesa.enumerations.GroupStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
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


	@Override
	public GroupeDTO save(GroupeDTO g) {
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
		Optional<Groupe> groupe = groupeRepository.findById(id);
		GroupeDTO dto = GroupeDTO.fromEntity(groupe.get());
		return Optional.of(dto).orElseThrow(() ->
				new ResourceNotFoundException(" Aucune Entreprise avec id =" + id + " n'ete  trouver ",
						ErrorCode.GROUPE_NOT_FOUND));

	}

	@Override
	public void delete(Long id) {
		if (id == null) {
			log.error(" GROUPE ID IS NULL ");
			return;
		}
		groupeRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		groupeRepository.deleteAll();
	}

	@Override
	public GroupeDTO updateSatus(Long id, GroupStatus status) {
		Optional<Groupe> Data = groupeRepository.findById(id);
		Groupe saved = null;
		if (Data.isPresent()) {
			Groupe groupe = Data.get();

			if (GroupStatus.ACTIVE == status) {
				groupe.setGroupStatus(GroupStatus.PENDING);
			}
			if (GroupStatus.PENDING == status) {
				groupe.setGroupStatus(GroupStatus.BLOCKED);
			}
			if (GroupStatus.BLOCKED == status) {
				groupe.setGroupStatus(GroupStatus.ACTIVE);
			}

			saved = groupeRepository.save(groupe);
		}
		return GroupeDTO.fromEntity(saved);
	}
}


    
    
    
    
    
    
    
    
    
    




