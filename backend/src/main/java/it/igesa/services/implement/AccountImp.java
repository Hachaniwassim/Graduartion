package it.igesa.services.implement;
import it.igesa.config.EmailService;
import it.igesa.dto.AccountDTO;
import it.igesa.domaine.Account;
import it.igesa.enumerations.ERole;
import it.igesa.payload.request.ChangePasswordRequest;
import it.igesa.enumerations.AccountStatus;
import it.igesa.enumerations.ErrorCode;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.services.IauthService;
import it.igesa.services.UserDetailsImpl;
import it.igesa.repository.IgroupeRepository;
import it.igesa.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static it.igesa.enumerations.ERole.ROLE_ADMIN;

/**
 *
 * @author Tarchoun Abir
 *
 */
@Service
public class AccountImp implements IauthService {
    @Autowired
    AccountRepository userRepository;
    @Autowired
    IgroupeRepository igroupeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    EmailService emailService;
    private static final Logger log = LoggerFactory.getLogger(AccountImp.class);


    //====================== Add Account ===============================//
    public AccountDTO save(AccountDTO account) {
        account.setAccountStatus(AccountStatus.PENDING);
        Account saved = userRepository.save(AccountDTO.toEntity(account));
        return AccountDTO.fromEntity(saved);
    }


    //====================== find All===============================//
    public List<AccountDTO> findAll() {
        return userRepository.findAll().stream()
                .map(AccountDTO::fromEntity)
                .collect(Collectors.toList());
    }


    //========================= Delete ===============================//
    public void delete(Long id) {
        if (id == null) {
            log.error("  ID IS NULL ");
            return;
        }
        userRepository.deleteById(id);
    }



    //========================== find by id ===============================//
    @Override
    public AccountDTO findById(Long id) {
        Optional<Account> account = userRepository.findById(id);
        AccountDTO dto = AccountDTO.fromEntity(account.get());
        return Optional.of(dto).orElseThrow(() ->
                new ResourceNotFoundException(" Aucune account avec id =" + id + " n'ete  trouver ", ErrorCode.ACCOUNT_NOT_VALID));
    }



    //================================ update Status ===============================//
    @Override
    public Account updateSatus(Long id, AccountStatus status) throws MessagingException {
        Optional<Account> Data = userRepository.findById(id);
        Account saved = null;
        if (Data.isPresent()) {
            Account account = Data.get();

            if (AccountStatus.ACTIVE == status) {
                account.setAccountStatus(AccountStatus.BLOCKED);
                emailService.sendNotifBlockedAccount(account.getEmail(),account.getUsername());
            }
            if (AccountStatus.PENDING == status) {
                account.setAccountStatus(AccountStatus.ACTIVE);
                emailService.sendNotif( account.getEmail(),account.getUsername());
            }
            if (AccountStatus.BLOCKED == status) {
                account.setAccountStatus(AccountStatus.PENDING);

            }

            saved = userRepository.save(account);
        }
        return saved;

    }


    //===============================  Changes Password ==============================//

    @Override
    public boolean changePassword(ChangePasswordRequest request) {
        UserDetailsImpl userDetails = getIdentity();
        if (null == userDetails) {
            return false;
        }
        Optional<Account> optionalAccount = userRepository.findByUsername(userDetails.getUsername());
        if (!optionalAccount.isPresent()) {
            return false;
        }
        Account account = optionalAccount.get();
        if (passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            account.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(account);
            return true;
        }
        return false;
    }

     /**
      * @return current entreprise :: <==================> :: identity of user
      */
        public UserDetailsImpl getIdentity() {
         if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetailsImpl)
            return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         return null;

    }
    /**
     * @update current user info ==============================>
     */

    @Override
    public void updateCUrrentUser(String username, String email, String fiscaleCode,Long idAccount){
        userRepository.updateCUrrentUser(username,email,fiscaleCode,idAccount);
    }

    @Override
    public void assignGroup(Long groupe_id,Long id){
        userRepository.assignGroupe(groupe_id,id);
    }


}