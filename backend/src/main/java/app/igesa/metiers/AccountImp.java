package app.igesa.metiers;
import app.igesa.entity.Account;
import app.igesa.entity.ERole;
import app.igesa.entity.Groupe;
import app.igesa.entity.Role;
import app.igesa.enumerations.AccountStatus;
import app.igesa.enumerations.GroupStatus;
import app.igesa.repository.IgroupeRepository;
import app.igesa.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class AccountImp implements Iaccount {
    @Autowired
    AccountRepository userRepository;
    @Autowired
    IgroupeRepository igroupeRepository;

    private static final Logger log = LoggerFactory.getLogger(Account.class);
    public Account save(Account account) {
        Groupe groupe = new Groupe();
        groupe.setId(account.getGroupId());
        account.setGroupe(groupe);
        account.setAccountStatus(AccountStatus.PENDING);
        return userRepository.save(account);
    }

    public Optional<Account> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<Account> findAll() {
        return userRepository.findAll();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Account updateSatus(Long id, AccountStatus status) {
        Optional<Account> Data = userRepository.findById(id);
        Account saved = null;
        if (Data.isPresent()) {
            Account account= Data.get();

            if (AccountStatus.ACTIVE == status) {
                account.setAccountStatus(AccountStatus.PENDING);
            }
            if (AccountStatus.PENDING == status) {
                account.setAccountStatus(AccountStatus.BLOCKED);
            }
            if (AccountStatus.BLOCKED == status) {
                account.setAccountStatus(AccountStatus.ACTIVE);
            }

            saved = userRepository.save(account);
        }
        return saved;
    }
}

