package app.igesa.metiers;

import app.igesa.dto.AccountDTO;
import app.igesa.entity.Account;
import app.igesa.entity.ChangePasswordRequest;
import app.igesa.enumerations.AccountStatus;
import org.springframework.security.core.userdetails.UserDetails;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

public interface Iaccount {
    public Account updateSatus(Long id, AccountStatus status) throws MessagingException;
    AccountDTO save(AccountDTO account);
    public AccountDTO findById(Long id);
    public List<AccountDTO> findAll();
    public void delete(Long id);
    UserDetailsImpl getIdentity();
    boolean changePassword(ChangePasswordRequest request);

}
