package it.igesa.resources;
import it.igesa.domaine.FileInfo;
import it.igesa.enumerations.ImageTypes;
import it.igesa.upload.FilesStorageService;
import it.igesa.upload.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Tarchoun Abir
 *
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FilesController {


  /*********************************************************
   *
   * Api  PRIVATE_API : with token
   *
   *********************************************************/

  private final String PUBLIC_API = "api/upload";
  private final String PRIVATE_API = "api/private/upload";

  @Autowired
  FilesStorageService storageService;

  @GetMapping(PRIVATE_API + "/files")
  public ResponseEntity<List<FileInfo>> getListFiles() {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();

      String url = MvcUriComponentsBuilder

              .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();


      return new FileInfo(filename, url);
    }).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);


  }

  @GetMapping(PRIVATE_API + "/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename, @PathVariable Integer id) {
    Resource file = storageService.load(ImageTypes.valueOf(filename), id);
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }

  @DeleteMapping(PRIVATE_API + "/files/all")
  public void deleteAll() {
    storageService.deleteAll();
  }


  @PostMapping(PRIVATE_API + "/{fileType}/{imageName}/{id_entreprise}")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable ImageTypes fileType,@PathVariable String imageName,@PathVariable Long id_entreprise) {
    String message = "";
    try {
      storageService.uploadImage(file, fileType,imageName,id_entreprise);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }


  @GetMapping(PRIVATE_API + "/{fileType}/{id_enterprise}")
  public ResponseEntity<String> getImage(@PathVariable(value = "id_enterprise", required = false) Long id_enterprise,
                                        // @PathVariable("parentId") Long id,
                                         @PathVariable("fileType") ImageTypes type) {
    return storageService.loadImage(type,id_enterprise);
  }

}