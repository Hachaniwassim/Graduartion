package app.igesa.upload;
import app.igesa.enumerations.ImageTypes;
import app.igesa.enumerations.PagesTypes;
import app.igesa.metiers.Ientreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  private final Path root = Paths.get("uploads");

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
      String filePath = "Enterprise-" + enterpriseService.getCurrentEnterprise().getId() + "/" + fileType + "-" + id + ".jpg";
      Files.copy(file.getInputStream(), this.root.resolve(filePath));
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
  public Resource load(ImageTypes fileType, Integer id) {
    try {

      String filePath = "Enterprise-" + enterpriseService.getCurrentEnterprise().getId() + "/" + fileType + "-" + id + ".jpg";
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

}
