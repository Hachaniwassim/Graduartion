package app.igesa.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import app.igesa.dto.PlateformeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.igesa.metiers.Iplateforme;
import app.igesa.repository.IplateformeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "PLATEFORME" )
@RestController
public class PlateformeController {

    private static final Logger log = LoggerFactory.getLogger(PlateformeController.class);
	@Autowired
	 private Iplateforme plateformeservice;
	@Autowired
	IplateformeRepository platformeRepository;

	@ApiOperation(value="GET PUBLISHED PLATEFORME", response = PlateformeDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Plateforme was FOUND Successfully"),
			@ApiResponse(code=400,message="Plateforme not FOUND")

	})
	 @RequestMapping(value="/plateforme/published",method =RequestMethod.GET)
	  public ResponseEntity<List<PlateformeDTO>> findByPublished() {
	    try {
	      List<PlateformeDTO> plateforme = platformeRepository.findByPublished(true);
	      if (plateforme.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(plateforme, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }



	@RequestMapping(value="/plateforme",method =RequestMethod.POST)
	@ApiOperation(value="ADD PLATEFORME",notes="SAUVGARDER PLATEFORME", response = PlateformeDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Plateforme was saved Successfully"),
			@ApiResponse(code=400,message="Plateforme not valid")

	})
	ResponseEntity<PlateformeDTO> save( @RequestBody PlateformeDTO p) {
		log.debug(" HTTP POST {}",p);
		return new ResponseEntity<>(plateformeservice.save(p),HttpStatus.CREATED);
	}


	@RequestMapping(value="/plateforme",method =RequestMethod.GET)
	@ApiOperation(value="GET A LIST OF PLATEFORME ", responseContainer  = "Collection<PlateformeDTO>")
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Plateforme was found successfully")

	})
	public ResponseEntity<Collection<PlateformeDTO>> view() {
		log.debug(" HTTP GET ALL PLATEFORME {}");
		return new ResponseEntity<>( plateformeservice.view(),HttpStatus.OK);
	}


	@RequestMapping(value="/plateforme/{id}",method =RequestMethod.GET)
	@ApiOperation(value=" GET PLATEFORME BY ID ",notes="GET AND SEARCH FOR PLATEFORMEBY ID ", response = PlateformeDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Plateforme was found successfully with the provided id"),
			@ApiResponse(code=404,message="No Plateforme is found with the provided id ")

	})
	public ResponseEntity<Optional<PlateformeDTO> >findById(@PathVariable Long id) {
		log.debug(" HTTP GET Plateforme BY ID {}",id);
		return new ResponseEntity<>( plateformeservice.findById(id),HttpStatus.OK);
	}


	@RequestMapping(value="/plateforme",method =RequestMethod.PUT)
	@ApiOperation(value="UPDATE PLATEFORME  ",response = PlateformeDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Plateforme was updated successfully")

	})
	public ResponseEntity<PlateformeDTO> update(@RequestBody PlateformeDTO p) {
		return new ResponseEntity<>(plateformeservice.save(p),HttpStatus.CREATED);
	}

	@RequestMapping(value="/plateforme/{id}",method =RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value="DELETE PLATEFORME BY ID ",response = PlateformeDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Plateforme was Deleted successfully")

	})
	public void delete(@PathVariable Long id) {

		log.debug(" HTTP DELETE PLATEFORMEBY ID {}",id);

		plateformeservice.delete(id);
	}

}
