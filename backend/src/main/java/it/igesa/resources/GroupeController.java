package it.igesa.resources;
import it.igesa.domaine.Groupe;
import it.igesa.enumerations.GroupStatus;
import it.igesa.services.IauthService;
import it.igesa.payload.request.AssignRequest;
import it.igesa.payload.response.MessageResponse;
import it.igesa.repository.IgroupeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import it.igesa.dto.GroupeDTO;
import it.igesa.services.Igroupe;


/**
 *
 * @author Tarchoun Abir
 *
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = "GROUP")
public class GroupeController {



	/**********************************************
	 *
	 * @Api PRIVATE_API : with token
	 *
	 ****/

	//private final String PUBLIC_API = "/api/groupe";
	private final String PRIVATE_API = "/api/private/groupe";

	private static final Logger log = LoggerFactory.getLogger(GroupeController.class);

	@Autowired
	private Igroupe groupeservice;
	@Autowired
	private IgroupeRepository igroupeRepository;
	@Autowired
	IauthService accountservice;



	@RequestMapping(value = PRIVATE_API, method = RequestMethod.POST)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
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


	@RequestMapping(value = PRIVATE_API, method = RequestMethod.GET)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
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


	@RequestMapping(value = PRIVATE_API + "/{id}", method = RequestMethod.GET)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
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

	@RequestMapping(value = PRIVATE_API + "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@ResponseBody
	@ApiOperation(value = "DELETE GROUPE BY ID ", response = GroupeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Groupe was Deleted successfully"),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed")

	})
	public ResponseEntity deleteGroupe(@PathVariable Long id) {
		log.debug(" HTTP DELETE {}", id);
		groupeservice.delete(id);
		return new ResponseEntity<>("{code :200 ,msg : deleted successfully}",HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_API, method = RequestMethod.PUT)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
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


	public ResponseEntity<HttpStatus> deleteAllGroupe() {
		try {
			groupeservice.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = PRIVATE_API + "/toggle-status/{id}", method = RequestMethod.PUT)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@ApiOperation(value = "UPDATE GROUPE BY Status ", response = GroupeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Groupe was updated successfully"),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed"),
	})
	public ResponseEntity<GroupeDTO> updateStatus(@PathVariable("id") long id, @RequestBody GroupStatus status) {
		return new ResponseEntity<>(groupeservice.updateSatus(id, status), HttpStatus.CREATED);
	}



	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping(value = PRIVATE_API + "/current-groupe")
	public Groupe getCurrentGroup() {
		return groupeservice.getCurrentGroup();
	}

	/**
	 * @param  assignRequest
	 *
	 */
	@RequestMapping(value = PRIVATE_API + "/assign-groupe", method = RequestMethod.POST)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@ApiOperation(value="ASSIGN Group To Account",notes="ASSIGN GROUP")
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Group was assigned Successfully"),
			@ApiResponse(code=400,message="Group not valid"),
			@ApiResponse(code=401,message="Unauthorized , without authority or permission"),
			@ApiResponse( code=403, message="not permitted or allowed"),

	})
	ResponseEntity assignEGroupe(@RequestBody AssignRequest assignRequest){
		accountservice.assignGroup(assignRequest.getId_groupe(),assignRequest.getId_account());
		return ResponseEntity.ok(new MessageResponse("groupe assigned successfully"));
}
}
