package it.igesa.upload;

import it.igesa.enumerations.ImageTypes;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
  public void init();

  //public void save(MultipartFile file, ImageTypes fileType, Integer id,Long id_entreprise);

  public Resource load(ImageTypes fileType, Integer id);

  public void deleteAll();
  public void deleteById( Long id);

  public Stream<Path> loadAll();

  String uploadImage(MultipartFile file, ImageTypes imageType,String imageName,Long id_enterprise);

  ResponseEntity<String> loadImage(ImageTypes imageType,Long id_entreprise);

  }
