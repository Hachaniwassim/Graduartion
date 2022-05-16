package app.igesa.metiers;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.uploader.UploadMetaData;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.InputStream;

@Service
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class FlickrImpl implements Iflicker{

    private Flickr flickr;



    @Override
    public String savePhoto(InputStream image, String title) throws FlickrException {
        UploadMetaData uploadMetaData =new UploadMetaData();
        uploadMetaData.setTitle(title);
        String photoID =flickr.getUploader().upload(image,uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(photoID).getMedium640Url();
    }


}
