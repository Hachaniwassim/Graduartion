package it.igesa.upload;
import it.igesa.enumerations.ImageTypes;
import it.igesa.services.Ientreprise;
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
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  private final Path root = Paths.get("${igesa.images.path}");

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
  public String uploadImage(MultipartFile file, ImageTypes imageType ,String imageName,Long id_entreprise) {
    if (file == null) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    if (file.getContentType() == null || (file.getContentType() != null && !file.getContentType().startsWith("image"))) {
      throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This file is not an image");
    }
    String baseDestination = this.path + "Enterprise-" + id_entreprise +
            "/" + imageType.name().toLowerCase().replace("_", "-") + "/" ;
    Path imagePath = Paths.get(baseDestination);
    if (!imagePath.toFile().exists()) {

      try {
        Files.createDirectories(Paths.get(baseDestination));
      } catch (IOException e) {
      System.out.println("upload failed");
      }

    }
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    Random random = new Random();
    //String ImageName = String.valueOf(random.ints(97, 122 + 1));
    String fileExtension = getExtensionByStringHandling(file.getOriginalFilename()).orElse(file.getContentType().substring(6));
    String filePath = baseDestination + imageName + "." + fileExtension;

    try {

      Files.copy(file.getInputStream(),
              Paths.get(filePath),
              StandardCopyOption.REPLACE_EXISTING
      );

    } catch (IOException e) {

      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return imageName + '.' + fileExtension;
  }


  private Optional<String> getExtensionByStringHandling(String filename) {
    return Optional.ofNullable(filename)
            .filter(f -> f.contains("."))
            .map(f -> f.substring(filename.lastIndexOf('.') + 1));
  }


  @Override
  public ResponseEntity<String> loadImage(ImageTypes imageType, Long id_enterprise) {

    File file = null;
    String imageFile = null;
    try {
      switch (imageType) {
        case PRODUCT:
          file = ResourceUtils.getFile(path + "Enterprise-" + id_enterprise+ "/" + ImageTypes.PRODUCT + "/" +  ".jpg");
          break;
        case NEWS:
          file = ResourceUtils.getFile(path + "Enterprise-" + id_enterprise + "/" + ImageTypes.NEWS + ".jpg");
          break;
        case LINKS:
          file = ResourceUtils.getFile(path + "Enterprise-" + id_enterprise + "/" + ImageTypes.LINKS + ".jpg");
          break;
        case BLOG_POST:
          file = ResourceUtils.getFile(path + "Enterprise-" + id_enterprise + "/" + ImageTypes.BLOG_POST + ".jpg");
        case CATEGORY:
          file = ResourceUtils.getFile(path + "Enterprise-" + id_enterprise + "/" + ImageTypes.CATEGORY + ".jpg");
          break;
        case CAROUSEL:
          file = ResourceUtils.getFile(path + "Enterprise-" + id_enterprise + "/" + ImageTypes.CAROUSEL + ".jpg");
        case CLIENT:
          file = ResourceUtils.getFile(path + "Enterprise-" + id_enterprise + "/" + ImageTypes.CLIENT + ".jpg");
        case ICON:
          file = ResourceUtils.getFile(path + "Enterprise-" + id_enterprise + "/" + ImageTypes.ICON+ ".jpg");
        case BRAND_LOGO:
          file = ResourceUtils.getFile(path + "Enterprise-" + id_enterprise + "/" + ImageTypes.BRAND_LOGO + ".jpg");
        case Flag:
          file = ResourceUtils.getFile(path + "Enterprise-" + id_enterprise + "/" + ImageTypes.Flag + ".jpg");
        case ASSISTANCE:
          file = ResourceUtils.getFile(path + "Enterprise-" + id_enterprise + "/" + ImageTypes.ASSISTANCE + ".jpg");
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
