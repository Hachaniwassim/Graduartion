package app.igesa.controller;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.igesa.dto.EntrepriseDTO;
import app.igesa.metiers.Ientreprise;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "ENTREPRISE")
@RestController
public class EntrepriseController {
	 private static final Logger log = LoggerFactory.getLogger(EntrepriseController.class);
	 
	 @Autowired
	 private Ientreprise entrepriseservice ;
	 

	@RequestMapping(value="/entreprise",method =RequestMethod.POST)
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


	@RequestMapping(value="/entreprise",method =RequestMethod.GET)
	@ApiOperation(value="GET A LIST OF ENTREPRISE ", responseContainer  = "Collection<EntrepriseDTO>")
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Enterprise was found successfully"),
			@ApiResponse( code=404, message="Entreprise Not found "),
			@ApiResponse(code=401,message="Unauthorized , without authority or permission"),
			@ApiResponse( code=403, message="not permitted or allowed"),
			
	})
	public ResponseEntity<Collection<EntrepriseDTO>> view() {
		log.debug(" HTTP GET ALL ENTREPRISE {}");
		return new ResponseEntity<>( entrepriseservice.view(),HttpStatus.OK);
	}


	@RequestMapping(value="/entreprise/{id}",method =RequestMethod.GET)
	@ApiOperation(value=" GET ENTREPRISE BY ID ",notes="GET AND SEARCH FOR ENTREPRISE BY ID ", response = EntrepriseDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Enterprise was found successfully with the provided id"),
			@ApiResponse(code=404,message="No Entreprise is found with the provided id "),
			@ApiResponse(code=401,message="Unauthorized , without authority or permission"),
			@ApiResponse( code=403, message="not permitted or allowed"),
			
	})
	public ResponseEntity<Optional<EntrepriseDTO> >findById(@PathVariable Long id) {
		log.debug(" HTTP GET ENTREPRISE BY ID {}",id);
	 return new ResponseEntity<>( entrepriseservice.findById(id),HttpStatus.OK);
	}


	@RequestMapping(value="/entreprise",method =RequestMethod.PUT)
	@ApiOperation(value="UPDATE ENTREPRISE BY ID ",response = EntrepriseDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Enterprise was updated successfully"),
			@ApiResponse(code=401,message="Unauthorized , without authority or permission"),
			@ApiResponse( code=403, message="not permitted or allowed"),
			
	})
	public ResponseEntity<EntrepriseDTO> update( @RequestBody EntrepriseDTO e) {
		return new ResponseEntity<>(entrepriseservice.save(e),HttpStatus.CREATED);
	}

	@RequestMapping(value="/entreprise/{id}",method =RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value="DELETE ENTREPRISE BY ID ",response = EntrepriseDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Enterprise was Deleted successfully"),
			@ApiResponse(code=401,message="Unauthorized , without authority or permission"),
			@ApiResponse( code=403, message="not permitted or allowed")
			
	})
	public void delete(@PathVariable Long id) {
		
		log.debug(" HTTP DELETE ENTREPRISE BY ID {}",id);
		
		entrepriseservice.delete(id);
	}


	@RequestMapping(value="/entreprise/groupe/{id}",method =RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="DELETE ENTREPRISE BY groupe id> ",response = EntrepriseDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="groupe was foundsuccessfully"),
			@ApiResponse(code=401,message="Unauthorized , without authority or permission"),
			@ApiResponse( code=403, message="not permitted or allowed")

	})
	public List<EntrepriseDTO> getEntrepriseByGroupe(@PathVariable Long id) {
		return entrepriseservice.getEntrepriseByGroupe(id);
	}



	@RequestMapping(value="/entreprise/companyname",method =RequestMethod.GET)
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
	
}
