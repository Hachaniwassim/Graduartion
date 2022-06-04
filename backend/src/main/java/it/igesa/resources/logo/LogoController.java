package it.igesa.controller.logo;


import it.igesa.dto.LogoDTO;
import it.igesa.services.logo.ILogo;
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

/**
 *
 * @author Tarchoun Abir
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "Logo")
public class LogoController {

    /**************************
     *
     *@PRIVATE_API : with token
     *
     ******/
    private final String PRIVATE_API = "/api/private/logo";
    @Autowired
    ILogo iLogoService;




    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value=PRIVATE_API + "/post-brand",method = RequestMethod.POST)
    @ApiOperation(value="UPDATE cookies   ",response = LogoDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="cookies was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<LogoDTO> SaveByEntreprise(@RequestBody LogoDTO brand) {
        return new ResponseEntity<>(iLogoService.save(brand), HttpStatus.CREATED);
    }


    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value=PRIVATE_API + "/update-brand",method =RequestMethod.PUT)

    @ApiOperation(value="UPDATE cookies   ",response = LogoDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="cookies was updated successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<LogoDTO>updateByEntreprise(@RequestBody LogoDTO brand) {
        return new ResponseEntity<>(iLogoService.save(brand),HttpStatus.CREATED);
    }

    /****
     *
     * @param id_entreprise
     * @return
     */

    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @RequestMapping(value=PRIVATE_API+"/list-logo/{id_entreprise}",method = RequestMethod.GET)
    @ApiOperation(value="GET LOGO",notes="get logo", responseContainer  = "Collection<BrandDTO>")
    @ApiResponses(value= {
            @ApiResponse(code=200,message=" founded Successfully"),
            @ApiResponse(code=400,message="not found"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed"),

    })
    public ResponseEntity<Collection<LogoDTO>> getCookiesByEntreprise(@PathVariable Long id_entreprise) {

        return new ResponseEntity<>( iLogoService.getAllByEntreprise(id_entreprise),HttpStatus.OK);
    }

    @RequestMapping(value=PRIVATE_API + "/{id}",method =RequestMethod.DELETE)
    @PreAuthorize( "hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    @ApiOperation(value=" delete  BY ID ",response = LogoDTO.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="logo was Deleted successfully"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="not permitted or allowed")

    })
    public ResponseEntity delete(@PathVariable Long id) {
       iLogoService.delete(id);
        return new ResponseEntity<>("{code :200 ,msg : deleted successfully}",HttpStatus.OK);
    }


}
