package it.igesa.payload.request;
import it.igesa.enumerations.AccountStatus;
import it.igesa.validators.PasswordMatches;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PasswordMatches
public class SignupRequest {

    private String username;
    private String email;
    @Size(min = 6, message = "{Size.userDto.password}")
    private String password;
    private String matchingPassword;
    private String fiscaleCode ;
    private Set<String> role;
    private AccountStatus accountStatus;


    public Set<String> getRole() {
      return this.role;
    }

    public void setRole(Set<String> role) {
      this.role = role;
    }

}
