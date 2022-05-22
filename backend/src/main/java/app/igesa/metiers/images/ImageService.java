package app.igesa.metiers.images;

import app.igesa.enumerations.ImageTypes;
import com.sun.istack.Nullable;
import org.hibernate.type.ImageType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Tarchoun Abir
 */
public interface ImageService {
    String load(Long id, ImageTypes imageType);
    public void upload(String file, Long id, ImageTypes imageType);
    public String upload2(String file64, String fileName, ImageTypes imageType);
    @Deprecated
    ResponseEntity<String> loadImage(Long id, ImageTypes imageType, Long eid);

    String uploadFile(MultipartFile file, ImageTypes imageType, @Nullable String filename, Long enterpriseId, Long groupId);

    byte[] loadImage(String imageType, String imageName, Long groupId, Long enterpriseId);

    byte[] loadFile(String imageType, String dir1, String dir2, String imageName, Long groupId, Long enterpriseId);

    abstract void deleteImage(ImageTypes imageType, String imageName, Long groupId, Long enterpriseId);
}
