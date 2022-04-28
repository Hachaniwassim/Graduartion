package app.igesa.payload.request;
import app.igesa.entity.Groupe;
import app.igesa.validators.PasswordMatches;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@PasswordMatches
public class SignupRequest {
    @NotEmpty
    private String username;
 
    @NotEmpty
    private String email;
    
    private Set<String> role;

    //private Groupe groupe ;


    @NotEmpty
    @Size(min = 6, message = "{Size.userDto.password}")
    private String password;
    @NotEmpty
    private String matchingPassword;
    @NotEmpty
    private String fiscaleCode ;


    public Set<String> getRole() {
      return this.role;
    }

    public String getFiscaleCode() {
        return fiscaleCode;
    }

    public void setFiscaleCode(String fiscaleCode) {
        this.fiscaleCode = fiscaleCode;
    }

    public void setRole(Set<String> role) {
      this.role = role;
    }
}
