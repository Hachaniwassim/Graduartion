package it.igesa.resources;
import it.igesa.strategy.StrategyPhotoContext;
import com.flickr4java.flickr.FlickrException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 *
 * @author Tarchoun Abir
 *
 */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(tags = "PHOTOS")
public class PhotoController {


    /**************************
     *
     *@PRIVATE_API : with token
     *
     ******/

    private final String PUBLIC_API = "api/photos";
    private final String PRIVATE_API = "api/private/photos";

    private StrategyPhotoContext strategyPhotoContext;

    @Autowired
    public PhotoController(StrategyPhotoContext strategyPhotoContext) {
        this.strategyPhotoContext = strategyPhotoContext;
    }




    @RequestMapping(value=PRIVATE_API + "/{id}/{title}/{context}",method = RequestMethod.POST)
    @ApiOperation(value="ADD PAGE",notes="SAUVGARDER PAGE", response = PhotoController.class)
    @ApiResponses(value= {
            @ApiResponse(code=200,message="Page was saved Successfully"),
            @ApiResponse(code=400,message="Page not valid"),
            @ApiResponse(code=401,message="Unauthorized , without authority or permission"),
            @ApiResponse( code=403, message="Not permitted or allowed"),

    })
    public Object savePhoto(@RequestPart("file") MultipartFile image,String context,Long id,String title) throws FlickrException, IOException {
        return strategyPhotoContext.savePhoto(context, id, image.getInputStream(), title);

    }
}
