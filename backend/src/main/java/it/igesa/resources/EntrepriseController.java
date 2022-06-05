package it.igesa.resources;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import it.igesa.domaine.Entreprise;
import it.igesa.services.IauthService;
import it.igesa.payload.request.EntreprisesByGroupRequest;
import it.igesa.repository.IentrepriseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import it.igesa.dto.EntrepriseDTO;
import it.igesa.services.Ientreprise;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 *
 * @author Tarchoun Abir
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = "ENTREPRISE")
@RestController
public class EntrepriseController {



	/**************************
	 *
	 *@PRIVATE_API : with token
	 *
	 ******/
	private final String PRIVATE_API = "/api/private/entreprise";

	@Autowired
	private Ientreprise entrepriseservice ;
	@Autowired
    IentrepriseRepository ientrepriseRepository;
	@Autowired
	IauthService accountservice;

	/** logger for debug : warning : success **/
	private static final Logger log = LoggerFactory.getLogger(EntrepriseController.class);


	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@RequestMapping(value=PRIVATE_API,method =RequestMethod.POST)
	@ApiOperation(value="ADD ENTREPRISE",notes="SAUVGARDER ENTREPRISE", response = EntrepriseDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Entreprise was saved Successfully"),
			@ApiResponse(code=400,message="Entreprise not valid"),
			@ApiResponse(code=401,message="Unauthorized , without authority or permission"),
			@ApiResponse( code=403, message="not permitted or allowed"),

	})
	ResponseEntity<EntrepriseDTO> save(@RequestBody EntrepriseDTO e) {
		log.debug(" HTTP POST {}",e);
		return new ResponseEntity<> (entrepriseservice.save(e),HttpStatus.CREATED);
	}

	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@RequestMapping(value=PRIVATE_API,method =RequestMethod.GET)
	@ApiOperation(value="GET A LIST OF ENTREPRISE ", responseContainer  = "Collection<EntrepriseDTO>")
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Enterprise was found successfully"),
			@ApiResponse( code=404, message="Entreprise Not found "),
			@ApiResponse(code=401,message="Unauthorized , without authority or permission"),
			@ApiResponse( code=403, message="not permitted or allowed"),

	})
	public ResponseEntity<Collection<EntrepriseDTO>> view() {
		log.debug(" <================= HTTP GET ALL ENTREPRISE {}=================>");
		return new ResponseEntity<>( entrepriseservice.view(),HttpStatus.OK);
	}

	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.GET)
	@ApiOperation(value=" GET ENTREPRISE BY ID ",notes="GET AND SEARCH FOR ENTREPRISE BY ID ", response = EntrepriseDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Enterprise was found successfully with the provided id"),
			@ApiResponse(code=404,message="No Entreprise is found with the provided id "),
			@ApiResponse(code=401,message="Unauthorized , without authority or permission"),
			@ApiResponse( code=403, message="not permitted or allowed"),

	})
	public ResponseEntity<Optional<EntrepriseDTO> >findById(@PathVariable Long id) {
		log.debug(" <===================HTTP GET ENTREPRISE BY ID {}====================>",id);
		return new ResponseEntity ( entrepriseservice.findById(id),HttpStatus.OK);
	}


	@RequestMapping(value=PRIVATE_API,method =RequestMethod.PUT)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@ApiOperation(value="UPDATE ENTREPRISE BY ID ",response = EntrepriseDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Enterprise was updated successfully"),
			@ApiResponse(code=401,message="Unauthorized , without authority or permission"),
			@ApiResponse( code=403, message="not permitted or allowed"),

	})
	public ResponseEntity<EntrepriseDTO> update( @RequestBody EntrepriseDTO e) {
		return new ResponseEntity<>(entrepriseservice.save(e),HttpStatus.CREATED);
	}
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value="DELETE ENTREPRISE BY ID ",response = EntrepriseDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Enterprise was Deleted successfully"),
			@ApiResponse(code=401,message="Unauthorized , without authority or permission"),
			@ApiResponse( code=403, message="not permitted or allowed")

	})
	public ResponseEntity delete(@PathVariable Long id) {

		log.debug(" <=================== HTTP DELETE ENTREPRISE BY ID {}====================>",id);

		entrepriseservice.delete(id);

		return new ResponseEntity<>("{code :200 ,msg : deleted successfully}",HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_API + "/groupe", method = RequestMethod.POST)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@ResponseBody
	@ApiOperation(value = "DELETE ENTREPRISE BY groupe id> ", response = EntrepriseDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "groupe was foundsuccessfully"),
			@ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
			@ApiResponse(code = 403, message = "not permitted or allowed")

	})
	public List<EntrepriseDTO> getEntrepriseByGroupe(@RequestBody EntreprisesByGroupRequest entreprisesByGroupRequest) {
		return entrepriseservice.getEntrepriseByGroupe(entreprisesByGroupRequest.getId_group());
	}


	@RequestMapping(value=PRIVATE_API +"/companyname",method =RequestMethod.GET)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@ResponseBody
	@ApiOperation(value=" GET Entreprise BY Companyname",notes="GET AND SEARCH FOR Entreprise BY companyname ", response = EntrepriseDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Entreprise was found successfully with the provided companyname"),
			@ApiResponse(code=404,message="No entreprise  is found with the provided companyname"),
			@ApiResponse(code=401,message="Unauthorized , without authority or permission"),
			@ApiResponse( code=403, message="not permitted or allowed"),

	})

	public List<EntrepriseDTO> FindEntrepriseByCompanyname( @RequestParam String companyname){

		return entrepriseservice.FindEntrepriseByCompanyname(companyname);
	}

	@RequestMapping(value=PRIVATE_API + "/current-entreprise",method =RequestMethod.GET)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@ResponseBody
	@ApiOperation(value=" GET Entreprise ",notes="Get and Search For Entreprise  ", response = EntrepriseDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Entreprise was found successfully"),
			@ApiResponse(code=404,message="No entreprise  "),
			@ApiResponse(code=401,message="Unauthorized , without authority or permission"),
			@ApiResponse( code=403, message="not permitted or allowed"),

	})

	    public Entreprise getCurrentEnterprise() {
		return entrepriseservice.getCurrentEnterprise();
	}
}


