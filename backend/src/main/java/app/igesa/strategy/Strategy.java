package app.igesa.strategy;
import com.flickr4java.flickr.FlickrException;
import java.io.InputStream;

public interface Strategy <T>{
    T savePhoto(InputStream image , String titre , Long id) throws FlickrException;

}
