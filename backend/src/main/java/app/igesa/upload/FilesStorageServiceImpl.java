package app.igesa.upload;
import app.igesa.enumerations.ImageTypes;
import app.igesa.enumerations.PagesTypes;
import app.igesa.metiers.Ientreprise;
import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  private final Path root = Paths.get("uploads");

  @Value("${igesa.images.path}")
  private String path;

  @Autowired
  FilesStorageService filesStorageService;
  @Autowired
  Ientreprise enterpriseService;
  public void init() {
    try {
      Files.createDirectory(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public void save(MultipartFile file, ImageTypes fileType, Integer id) {
    try {
      String filePath = "Enterprise-" + enterpriseService.getCurrentEnterprise().getId() + "/" + fileType + "/" + "test" + ".jpg";
      Files.copy(file.getInputStream(), this.root.resolve(filePath));
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
  public Resource load(ImageTypes fileType, Integer id) {
    try {

      String filePath = "Enterprise-" + enterpriseService.getCurrentEnterprise().getId() + "/" + fileType + "/" + id + ".jpg";
      Path file = root.resolve(filePath);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }

  @Override
  public void deleteById(Long id) {
    FileSystemUtils.deleteRecursively(root.toFile());

  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }



  @Override
  public String uploadImage(MultipartFile file, ImageTypes imageType, Long parentId) {
    if (file == null) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    if (file.getContentType() == null || (file.getContentType() != null && !file.getContentType().startsWith("image"))) {
      throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This file is not an image");
    }
    String baseDestination = this.path + "Enterprise-" + enterpriseService.getCurrentEnterprise().getId() +
            "/" + imageType.name().toLowerCase().replace("_", "-") + "/" ;
    Path imagePath = Paths.get(baseDestination);
    if (!imagePath.toFile().exists()) {

      try {
        Files.createDirectories(Paths.get(baseDestination));
      } catch (IOException e) {
      System.out.println("upload failed");
      }

    }

    String fileExtension = getExtensionByStringHandling(file.getOriginalFilename()).orElse(file.getContentType().substring(6));
    String filePath = baseDestination + parentId + "." + fileExtension;

    try {

      Files.copy(file.getInputStream(),
              Paths.get(filePath),
              StandardCopyOption.REPLACE_EXISTING
      );

    } catch (IOException e) {

      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return parentId + '.' + fileExtension;
  }


  private Optional<String> getExtensionByStringHandling(String filename) {
    return Optional.ofNullable(filename)
            .filter(f -> f.contains("."))
            .map(f -> f.substring(filename.lastIndexOf('.') + 1));
  }


  @Override
  public ResponseEntity<String> loadImage(Long id, ImageTypes imageType, Long eid) {
    if (eid == null) {
      eid = enterpriseService.getCurrentEnterprise().getId();
    }
    File file = null;
    String imageFile = null;
    try {
      switch (imageType) {
        case PRODUCT:
          file = ResourceUtils.getFile(path + "Enterprise-" + eid + "/" + ImageTypes.PRODUCT + "/" + id + ".jpg");
          break;
        case NEWS:
          file = ResourceUtils.getFile(path + "News/Enterprise-" + enterpriseService.getCurrentEnterprise().getId() + "/" + "news-" + id + ".jpg");
          break;
        default:
          new IllegalArgumentException();
      }

      if (file.exists()) {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        imageFile = "data:image/jpeg;base64," + java.util.Base64.getEncoder().encodeToString(fileContent);
      }


    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (imageFile == null) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity("\"" + imageFile.toString() + "\"", HttpStatus.OK);
  }


}
