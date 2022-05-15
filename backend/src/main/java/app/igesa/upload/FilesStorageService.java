package app.igesa.upload;

import app.igesa.enumerations.PagesTypes;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
  public void init();

  public void save(MultipartFile file, PagesTypes fileType, Integer id);
  public Resource load(PagesTypes fileType, Integer id);

  public void deleteAll();
  public void deleteById( Long id);

  public Stream<Path> loadAll();
}
