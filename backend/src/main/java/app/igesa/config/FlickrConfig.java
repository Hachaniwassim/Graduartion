package app.igesa.config;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import org.springframework.context.annotation.Bean;

//@Configuration
public class FlickrConfig {


    private String apiKey = "c5a8d5b0948e9858bd6a989495403c25";
    private String apiSecret = "f04e888ce591c683";
    private String appKey = "72157720838532597-873d0b6031d2fe72";
    private String appSecret = "a7031c1ba8f32524";


    /*@Bean
    public Flickr getFlickr() throws IOException, ExecutionException, InterruptedException, FlickrException {
        Flickr flickr=new Flickr(apiKey,apiSecret,new REST());
        OAuth10aService service= new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance(FlickrApi.FlickrPerm.DELETE));
        final Scanner scanner=new Scanner(System.in);
        final OAuth1RequestToken request = service.getRequestToken();
        final String authUrl =service.getAuthorizationUrl(request);
        System.out.println(authUrl);
        System.out.println("pass it here >>> ");
        final  String authVerification =scanner.nextLine();
        OAuth1AccessToken accessToken = service.getAccessToken(request,authVerification);
        System.out.println(accessToken.getToken());
        System.out.println(accessToken.getTokenSecret());
        Auth auth = flickr.getAuthInterface().checkToken(accessToken);
        System.out.println("----------------------------------------------");
        System.out.println(auth.getToken());
        System.out.println(auth.getTokenSecret());
        return flickr;
    }*/



    @Bean
    public Flickr getFlickr() {
        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());
        Auth auth = new Auth();
        auth.setPermission(Permission.DELETE);
        auth.setToken(appKey);
        auth.setTokenSecret(appSecret);
        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);
        flickr.setAuth(auth);
        return flickr;
    }

}
