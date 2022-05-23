package app.igesa.payload.request;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tarchoun Abir
 *
 */
@Getter
@Setter
public class UpdateProfilRequest {
    private String username;
    private String email;
    private String fiscaleCode;
}
