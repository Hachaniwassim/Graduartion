package app.igesa.metiers.images;
import app.igesa.enumerations.ImageTypes;
import app.igesa.metiers.Ientreprise;
import app.igesa.metiers.implement.GroupeImpl;
import com.sun.istack.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;
import static app.igesa.enumerations.ImageTypes.CAROUSEL;
import static app.igesa.enumerations.ImageTypes.PRODUCT;


/**
 * @author Tarchoun Abir
 *
 */
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Value("${igesa.images.path}")
    private String path;
    @Autowired
    private GroupeImpl igroupeService;
    @Autowired
    private Ientreprise ientrepriseService;

    @Override
    public void upload(String file, Long id, ImageTypes imageType) {
        if (file != null) {
            try {
                String destinationFile = null;
                String imageDataBytes = file.substring(file.indexOf(",") + 1);
                InputStream inputStream = new ByteArrayInputStream(Base64.decode(imageDataBytes.getBytes()));
                File directory = null;
                if (PRODUCT.equals(imageType)) {
                    directory = new File(path + "Product/Enterprise-" + ientrepriseService.getCurrentEnterprise().getId());
                    destinationFile = "product-" + id + ".jpg";
                } else {
                    new IllegalArgumentException();
                }
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                OutputStream outputStream = new FileOutputStream(new File(directory + "/" + destinationFile));
                byte[] byteArray = new byte[2048];   // A byte array for checking the end of data stream
                int length;   // length for data stream
                while ((length = inputStream.read(byteArray)) != -1) {
                    outputStream.write(byteArray, 0, length);   // Will write data to file byte by byte of size 2048
                }
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    @Override
    public String upload2(String file64, String fileName, ImageTypes imageType) {
        InputStream inputStream = new ByteArrayInputStream(Base64.decode(file64.getBytes()));

        String baseDestination = this.path +
                "GROUP-" + igroupeService.getCurrentGroup().getId() +
                "/" + "ENTERPRISE-" + ientrepriseService.getCurrentEnterprise().getId() + "/" +
                imageType.name().toLowerCase().replace("_", "-") + "/";
        Path imagePath = Paths.get(baseDestination);
        if (!imagePath.toFile().exists()) {
            try {
                Files.createDirectories(Paths.get(baseDestination));
            } catch (IOException e) {
                log.error("Error when creating {} folder: {}", imageType.name().toLowerCase(), e.getMessage());
            }
        }

        String filePath = baseDestination +  fileName;
        try {
            Files.copy(inputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Error when copying file : {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        System.out.println("file Path = " + filePath);

        return fileName;
    }

    @Override
    public String load(Long id, ImageTypes imageType) {
        File file = null;
        String imageFile = null;
        try {
            if (PRODUCT.equals(imageType)) {
                file = ResourceUtils.getFile(path + "Product/Enterprise-" + ientrepriseService.getCurrentEnterprise().getId() + "/" + "product-" + id + ".jpg");
            } else {
                new IllegalArgumentException();
            }
            //File is found
            System.out.println("File Found : " + file.exists());

            if (file.exists()) {
                byte[] fileContent = Files.readAllBytes(file.toPath());
                imageFile = "data:image/jpeg;base64," + java.util.Base64.getEncoder().encodeToString(fileContent);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageFile;
    }


    @Override
    public String uploadFile(MultipartFile file, ImageTypes imageType, @Nullable String filename,
                             Long enterpriseId, Long groupId) {
        if (file == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (file.getContentType() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This file is empty");
        }

        String baseDestination = this.path +
                "GROUP-" + groupId + "/" + "ENTERPRISE-" + enterpriseId + "/" +
                imageType.name().toLowerCase().replace("_", "-") + "/";
 //a verifier
        if(imageType.equals(CAROUSEL)) {
            String[] dirs = filename.split(";");
            baseDestination = baseDestination + dirs[0] + "/" + dirs[1] + "/";
            filename = dirs[2];
        }

        Path imagePath = Paths.get(baseDestination);
        if (!imagePath.toFile().exists()) {
            try {
                Files.createDirectories(Paths.get(baseDestination));
            } catch (IOException e) {
                log.error("Error when creating {} folder: {}", imageType.name().toLowerCase(), e.getMessage());
            }

        }

        if (filename == null) {
            filename = UUID.randomUUID().toString();
        }
        String fileExtension = getExtensionByStringHandling(file.getOriginalFilename()).orElse(file.getContentType().substring(6));
        String filePath = baseDestination + filename + "." + fileExtension;

        try {

            Files.copy(file.getInputStream(),
                    Paths.get(filePath),
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (IOException e) {
            log.error("Error when copying carousel image: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        System.out.println("file Path = " + filePath);

        return filename + "." + fileExtension;
    }

    @Override
    public byte[] loadImage(String imageType, String imageName, Long groupId, Long enterpriseId) {
        String fileFolder = this.path +
                "GROUP-" + groupId +
                "/" + "ENTERPRISE-" + enterpriseId + "/"
                + imageType + "/" +
                imageName;
        try {
            return Files.readAllBytes(Paths.get(fileFolder));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
        }
    }

    @Override
    public byte[] loadFile(String imageType, String dir1, String dir2, String imageName, Long groupId, Long enterpriseId) {
        String fileFolder = this.path +
                "GROUP-" + groupId
                + "/" + "ENTERPRISE-" + enterpriseId
                + "/" + imageType
                + "/" + dir1
                + "/" + dir2
                + "/" + imageName;
        try {
            return Files.readAllBytes(Paths.get(fileFolder));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
        }
    }

    public ResponseEntity<String> loadImage(Long id, ImageTypes imageType, Long eid) {
        if (eid == null) {
            eid = ientrepriseService.getCurrentEnterprise().getId();
        }
        File file = null;
        String imageFile = null;
        try {
            if (PRODUCT.equals(imageType)) {
                file = ResourceUtils.getFile(path + "Product/Enterprise-" + eid + "/" + "product-" + id + ".jpg");
            } else {
                throw new IllegalArgumentException();
            }

            if (file.exists()) {
                byte[] fileContent = Files.readAllBytes(file.toPath());
                imageFile = "data:image/jpeg;base64," + java.util.Base64.getEncoder().encodeToString(fileContent);
            }


        } catch (IOException e) {
            log.error("Error image :", e.getMessage());
        }
        if (imageFile == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("\"" + imageFile + "\"", HttpStatus.OK);
    }

    @Override
    public void deleteImage(ImageTypes imageType, String imageName, Long groupId, Long enterpriseId) {
        String imagePath = path
                + "GROUP-"
                + groupId + "/"
                + "ENTERPRISE-"
                + enterpriseId + "/"
                + imageType.name().toLowerCase().replace("_", "-")
                + "/" + imageName;
        try {
            Files.delete(Paths.get(imagePath));
        } catch (IOException e) {
            log.error("Error when deleting image: {}", e.getMessage());
        }
    }

    private Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf('.') + 1));
    }
}
