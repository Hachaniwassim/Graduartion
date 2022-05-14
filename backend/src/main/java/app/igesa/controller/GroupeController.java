package app.igesa.controller;
import app.igesa.entity.Groupe;
import app.igesa.enumerations.GroupStatus;
import app.igesa.repository.IgroupeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.igesa.dto.GroupeDTO;
import app.igesa.metiers.Igroupe;


/**
 * @author Tarchoun Abir#
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "GROUPE")
public class GroupeController {

	private static final Logger log = LoggerFactory.getLogger(GroupeController.class);
	@Autowired
	private Igroupe groupeservice;
	@Autowired
	private IgroupeRepository igroupeRepository;


	@RequestMapping(value = "/groupe", method = RequestMethod.POST)
	@ApiOperation(value = "ADD GROUPE", notes = "SAUVGARDER GROUPE", response = GroupeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Groupe was saved Successfully"),
			@ApiResponse(code = 400, message = "Groupe not valid"),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed"),

	})
	public GroupeDTO saveGroupe(@RequestBody GroupeDTO g) {
		log.debug(" HTTP POST {}", g);
		return groupeservice.save(g);
	}


	@RequestMapping(value = "/groupe", method = RequestMethod.GET)
	@ApiOperation(value = "GET A LIST OF GROUPE ", responseContainer = "Collection<GroupeDTO>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Groupe was found successfully"),
			@ApiResponse(code = 404, message = "GroupeNot found "),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed"),

	})
	public Collection<GroupeDTO> viewGroupe() {
		log.debug(" HTTP GET  ALL GROUPE {}");
		return groupeservice.view();
	}


	@RequestMapping(value = "/groupe/{id}", method = RequestMethod.GET)
	@ApiOperation(value = " GET GROUPE BY ID ", notes = "GET AND SEARCH FOR GROUPE BY ID ", response = GroupeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Groupe was found successfully with the provided id"),
			@ApiResponse(code = 404, message = "No Groupeis found with the provided id "),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed"),

	})
	public GroupeDTO findByIdGroupe(@PathVariable Long id) {
		log.debug(" HTTP FIND BY ID {}", id);
		return groupeservice.findById(id);
	}

	@RequestMapping(value = "/groupe/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "DELETE GROUPE BY ID ", response = GroupeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Groupe was Deleted successfully"),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed")

	})
	public void deleteGroupe(@PathVariable Long id) {
		log.debug(" HTTP DELETE {}", id);
		groupeservice.delete(id);
	}

	@RequestMapping(value = "/groupe", method = RequestMethod.PUT)
	@ApiOperation(value = "UPDATE GROUPE BY ID ", response = GroupeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Groupe was updated successfully"),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed"),
	})
	public ResponseEntity<GroupeDTO> updateGroupe(@RequestBody GroupeDTO g) {
		log.debug(" HTTP PUT {}");
		return new ResponseEntity<>(groupeservice.save(g), HttpStatus.CREATED);
	}


	@RequestMapping(value = "/groupe/active", method = RequestMethod.GET)
	@ApiOperation(value = "find by active groupe ", response = GroupeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Groupe was fouded successfully"),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed"),
	})
	public ResponseEntity<List<GroupeDTO>> findByActiveGroupe() {
		try {
			List<GroupeDTO> groupes = igroupeRepository.findByGroupStatus(GroupStatus.ACTIVE).stream().map(GroupeDTO::fromEntity).collect(Collectors.toList());
			if (groupes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(groupes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/groupe/confirmed", method = RequestMethod.GET)
	@ApiOperation(value = " find by confirmed   groupe", response = GroupeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Groupe was founded successfully"),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed"),
	})
	public ResponseEntity<List<GroupeDTO>> findByConfirmedGroupe() {
		try {
			List<GroupeDTO> groupes = igroupeRepository.findByGroupStatus(GroupStatus.ACTIVE).stream().map(GroupeDTO::fromEntity).collect(Collectors.toList());
			if (groupes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(groupes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value = "/groupe/deleted", method = RequestMethod.GET)
	@ApiOperation(value = " find by deleted   groupe", response = GroupeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Groupe was founded successfully"),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed"),
	})
	public ResponseEntity<List<GroupeDTO>> findByDeletedGroupe() {
		try {
			List<GroupeDTO> groupes = igroupeRepository.findByGroupStatus(GroupStatus.BLOCKED).stream().map(GroupeDTO::fromEntity).collect(Collectors.toList());
			if (groupes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(groupes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value = "/groupe", method = RequestMethod.DELETE)
	@ApiOperation(value = " delete all groupes ", response = GroupeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "all groupes was deleted successfully"),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed"),
	})
	public ResponseEntity<HttpStatus> deleteAllGroupe() {
		try {
			groupeservice.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/groupe/toggle-status/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "UPDATE GROUPE BY ID ", response = GroupeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Groupe was updated successfully"),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed"),
	})
	public ResponseEntity<GroupeDTO> updateStatus(@PathVariable("id") long id, @RequestBody GroupStatus status) {
		return new ResponseEntity<>(groupeservice.updateSatus(id, status), HttpStatus.CREATED);
	}
}