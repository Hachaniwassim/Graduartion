package it.igesa.resources;

import java.util.Collection;
import java.util.List;
import it.igesa.dto.CompanyBusinessDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.igesa.services.IcompanyBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Wassim Hachaani
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "COMPANYBUSINESS")
public class CompanyBusinessController {

    /**************************
     *
     *@PRIVATE_API : with token
     *
     ******/
    private final String PRIVATE_API = "/api/private/company";
    @Autowired
    IcompanyBusiness icompanyBusinessService ;



    private static final Logger log = LoggerFactory.getLogger(CompanyBusinessController.class);


    @RequestMapping(value=PRIVATE_API ,method = RequestMethod.POST)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="ADD COMPANYBUSINISS ",notes="SAUVGARDER COMPANYBUSINISS ", response = CompanyBusinessDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="CompanyBusiness was saved Successfully"),
            @ApiResponse(code=400,message="CompanyBusiness not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    ResponseEntity<CompanyBusinessDTO> save( @RequestBody CompanyBusinessDTO c) {
        log.debug(" HTTP POST {}",c);
        return new ResponseEntity<> (icompanyBusinessService.save(c), HttpStatus.CREATED);
    }


    @RequestMapping(value=PRIVATE_API ,method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value="GET A LIST OF COMPANY BUSINESS  ", responseContainer  = "Collection<CompanyBusinessDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message="CompanyBusiness  was found successfully"),
            @ApiResponse( code=404, message="CompanyBusiness Not found "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })

    public ResponseEntity<Collection<CompanyBusinessDTO>> view() {
        log.debug(" HTTP GET ALL COMPANYBUSINESS  {}");
        return new ResponseEntity<>( icompanyBusinessService.view(),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.GET)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ApiOperation(value=" GET COMPANYBUSINESS BY ID ",notes="GET AND SEARCH FOR COMPANYBUSINESS BY ID ", response = CompanyBusinessDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Company business was found successfully with the provided id"),
            @ApiResponse(code=404,message="No Company business  is found with the provided id "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })

    public ResponseEntity<CompanyBusinessDTO>findById(@PathVariable Long id) {
        log.debug(" HTTP GET COMPANY BUSINESS BY ID {}",id);
        return new ResponseEntity<>(icompanyBusinessService.findById(id),HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API ,method =RequestMethod.PUT)
    @ApiOperation(value="UPDATE COMPANY BUSINESS BY ID ",response = CompanyBusinessDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="companyBusines  was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })

    public ResponseEntity<CompanyBusinessDTO> update(@RequestBody CompanyBusinessDTO c) {
        return new ResponseEntity<>(icompanyBusinessService.save(c),HttpStatus.CREATED);
    }

    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value="DELETE COMPANY BUSINESS BY ID ",response = CompanyBusinessDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="COMPANY BUSINESS  was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public ResponseEntity delete(@PathVariable Long id) {

        log.debug(" HTTP DELETE COMPANY BUSINESS BY ID {}",id);
        icompanyBusinessService.delete(id);
        return new ResponseEntity<>("{code :200 ,msg : deleted successfully}",HttpStatus.OK);
    }


    @RequestMapping(value=PRIVATE_API + "/description",method =RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value=" GET COMPANYBUSINESS BY DESCRIPTION",notes="GET AND SEARCH FOR COMPANYBUSINESS BY DESCRIPTION ", response = CompanyBusinessDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Company business was found successfully with the provided Description"),
            @ApiResponse(code=404,message="No Company business  is found with the provided id "),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })

    public List<CompanyBusinessDTO> FindCompanyBusinessByDescription( @RequestParam String description){

        return icompanyBusinessService.FindCompanyBusinessByDescription(description);
    }
}
