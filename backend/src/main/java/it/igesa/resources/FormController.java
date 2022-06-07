package it.igesa.resources;

import it.igesa.dto.EntrepriseDTO;
import it.igesa.dto.FormDTO;
import it.igesa.dto.GroupeDTO;
import it.igesa.enumerations.ContactStatus;
import it.igesa.enumerations.GroupStatus;
import it.igesa.services.IformEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.util.Optional;

/**
 *
 * @author Tarchoun Abir
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "FORMENTITY")
public class FormController {



    /**************************
     *
     *@PRIVATE_API : with token
     *
     ******/
    private final String PRIVATE_API = "api/private/form";

    @Autowired
    IformEntity iformEntityService;

    private static final Logger log = LoggerFactory.getLogger(FormController.class);


    @RequestMapping(value=PRIVATE_API + "/post-form",method = RequestMethod.POST)
    @ApiOperation(value="ADD FORM ENTITY  ",notes="SAUVGARDER FORM ENTITY ", response = FormDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="FormEntity was saved Successfully"),
            @ApiResponse(code=400,message="FoemEntity not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<FormDTO> save( @RequestBody FormDTO f) {
        log.debug(" HTTP POST {}",f);
        return new ResponseEntity<> (iformEntityService.save(f), HttpStatus.CREATED);
    }


    @RequestMapping(value=PRIVATE_API + "/list-form/{id_entreprise}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET A LIST OF FORM ENTITY  ", responseContainer  = "Collection<FormDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="FormEntity was found successfully"),
            @ApiResponse( code=404, message="FormEntity Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<FormDTO>> view(@PathVariable Long id_entreprise) {
        log.debug(" HTTP GET ALL FORM ENTITY   {}");
        return new ResponseEntity<>( iformEntityService.view(id_entreprise),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value=" GET FORM ENTITY  BY ID ",notes="GET AND SEARCH FOR FORM ENTITY  BY ID ", response = FormDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="FormEntity was found successfully with the provided id"),
            @ApiResponse(code=404,message="No formEntity is found with the provided id "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Optional<FormDTO>>findById(@PathVariable Long id) {
        log.debug(" HTTP GET FORM ENTITY   BY ID {}",id);
        return new ResponseEntity<>( iformEntityService.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API,method =RequestMethod.PUT)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="UPDATE FORM ENTITY ",response = FormDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="FormEntity was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<FormDTO> update(@RequestBody FormDTO f) {
        return new ResponseEntity<>(iformEntityService.save(f),HttpStatus.CREATED);
    }

    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.DELETE)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value="DELETE FORM ENTITY BY ID ",response = EntrepriseDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message=" Formentity  was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public ResponseEntity delete(@PathVariable Long id) {

        log.debug(" HTTP DELETE FORM ENTITY   BY ID {}",id);
        iformEntityService.delete(id);
        return new ResponseEntity<>("{code :200 ,msg : deleted successfully}",HttpStatus.OK);
    }

    @RequestMapping(value = PRIVATE_API + "/toggle-status/{id}", method = RequestMethod.PUT)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value = "UPDATE GROUPE BY Status ", response = GroupeDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Groupe was updated successfully"),
            @ApiResponse(code = 401, message = "Unauthorized , without authority or permission"),
            @ApiResponse(code = 403, message = "not permitted or allowed"),
    })
    public ResponseEntity<FormDTO> updateStatus(@PathVariable("id") long id, @RequestBody ContactStatus status) throws MessagingException {
        return new ResponseEntity<>(iformEntityService.updateStatusContact(id, status), HttpStatus.CREATED);
    }


}
