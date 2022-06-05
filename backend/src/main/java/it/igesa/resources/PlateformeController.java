package it.igesa.resources;
import java.util.Collection;
import java.util.Optional;
import it.igesa.dto.PlateformeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import it.igesa.services.Iplateforme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tarchoun Abir
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = "PLATEFORME" )
@RestController
public class PlateformeController {



	/**************************
	 *
	 *@PRIVATE_API : with token
	 *
	 ******/

	private final String PRIVATE_API = "api/private/plateforme";
    private static final Logger log = LoggerFactory.getLogger(PlateformeController.class);

	@Autowired
	 private Iplateforme plateformeservice;

	@RequestMapping(value=PRIVATE_API ,method =RequestMethod.POST)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@ApiOperation(value="ADD PLATEFORME",notes="SAUVGARDER PLATEFORME", response = PlateformeDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Plateforme was saved Successfully"),
			@ApiResponse(code=400,message="Plateforme not valid")

	})
	ResponseEntity<PlateformeDTO> save( @RequestBody PlateformeDTO p) {
		log.debug(" <===================HTTP POST {} =========================>",p);
		return new ResponseEntity<>(plateformeservice.save(p),HttpStatus.CREATED);
	}


	@RequestMapping(value=PRIVATE_API ,method =RequestMethod.GET)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@ApiOperation(value="GET A LIST OF PLATEFORME ", responseContainer  = "Collection<PlateformeDTO>")
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Plateforme was found successfully")

	})
	public ResponseEntity<Collection<PlateformeDTO>> view(@PathVariable Long id_entreprise) {
		log.debug("<============================ HTTP GET ALL PLATEFORME {}==============================>");
		return new ResponseEntity<>( plateformeservice.getCurrentSiteInfo(id_entreprise),HttpStatus.OK);
	}


	@RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.GET)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@ApiOperation(value=" GET PLATEFORME BY ID ",notes="GET AND SEARCH FOR PLATEFORMEBY ID ", response = PlateformeDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Plateforme was found successfully with the provided id"),
			@ApiResponse(code=404,message="No Plateforme is found with the provided id ")

	})
	public ResponseEntity<Optional<PlateformeDTO> >findById(@PathVariable Long id) {
		log.debug("<=================HTTP GET Plateforme BY ID {}==================>",id);
		return new ResponseEntity<>( plateformeservice.findById(id),HttpStatus.OK);
	}


	@RequestMapping(value=PRIVATE_API ,method =RequestMethod.PUT)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@ApiOperation(value="UPDATE PLATEFORME  ",response = PlateformeDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Plateforme was updated successfully")

	})
	public ResponseEntity<PlateformeDTO> update(@RequestBody PlateformeDTO p) {

		log.debug("<=================HTTP PUT Plateforme {}==================>");
		return new ResponseEntity<>(plateformeservice.save(p),HttpStatus.CREATED);
	}

	@RequestMapping(value=PRIVATE_API  + "/{id}",method =RequestMethod.DELETE)
	@PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
	@ResponseBody
	@ApiOperation(value="DELETE PLATEFORME BY ID ",response = PlateformeDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Plateforme was Deleted successfully")

	})
	public ResponseEntity delete(@PathVariable Long id) {
		log.debug("<=================HTTP Delete plateforme{}==================>",id);
		log.debug(" HTTP DELETE PLATEFORMEBY ID {}",id);
		plateformeservice.delete(id);
        return new ResponseEntity<>("{code :200 ,msg : deleted successfully}",HttpStatus.OK);
	}

}
