package app.igesa.metiers;
import app.igesa.dto.AccountDTO;
import app.igesa.entity.Account;
import app.igesa.payload.request.ChangePasswordRequest;
import app.igesa.enumerations.AccountStatus;

import javax.mail.MessagingException;
import java.util.List;

/**
 *
 * @author Tarchoun Abir
 *
 */
public interface IauthService {

    public Account updateSatus(Long id, AccountStatus status) throws MessagingException;

    AccountDTO save(AccountDTO account);

    public AccountDTO findById(Long id);

    public List<AccountDTO> findAll();

    public void delete(Long id);

    boolean changePassword(ChangePasswordRequest request);

    UserDetailsImpl getIdentity();

    public void assignGroupe(Long groupe_id,Long id);

    void updateCUrrentUser(String username, String email, String fiscaleCode);
}
