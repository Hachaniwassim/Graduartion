package it.igesa.strategy;
import it.igesa.enumerations.ErrorCode;
import it.igesa.exceptions.InvalideEntityException;
import com.flickr4java.flickr.FlickrException;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;


@Service
public class StrategyPhotoContext {


    private BeanFactory beanFactory;
    private Strategy strategy;
    @Setter
    private String context;

    @Autowired
    public StrategyPhotoContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object savePhoto(String context , Long id , InputStream image , String title) throws FlickrException {
        DetermineContext(context);
        return strategy.savePhoto(image, title, id);

    }
    private void DetermineContext(String context){
        final String beanName = context + "Strategy";
        switch (context){
            case "Product":
               strategy = beanFactory.getBean(beanName,SaveProductPhoto.class);
                break;
            case "Post":
                strategy = beanFactory.getBean(beanName,SavePostPhoto.class);
                break;
            case "Config":
                strategy = beanFactory.getBean(beanName,SaveConfigPhoto.class);
                break;
            case "Language":
                strategy = beanFactory.getBean(beanName,SaveLanguagePhoto.class);

            case "Category":
                strategy = beanFactory.getBean(beanName,SaveCategoryPhoto.class);
                break;
            default:throw new InvalideEntityException("Context Not Recognized for Backup ", ErrorCode.UNKHNOWN_CONTEXT);
        }
    }

}
