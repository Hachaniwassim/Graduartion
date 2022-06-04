package it.igesa.services;
import it.igesa.dto.AccountDTO;
import it.igesa.domaine.Account;
import it.igesa.payload.request.ChangePasswordRequest;
import it.igesa.enumerations.AccountStatus;

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

    void updateCUrrentUser(String username, String email, String fiscaleCode,Long idAccount);

    void assignGroup(Long groupe_id, Long id);

}
