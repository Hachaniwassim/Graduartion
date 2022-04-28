package app.igesa.metiers;
import app.igesa.entity.Account;
import app.igesa.entity.Groupe;
import app.igesa.repository.IgroupeRepository;
import app.igesa.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class AccountImp {
    @Autowired
    AccountRepository userRepository;
    @Autowired
    IgroupeRepository igroupeRepository;

    private static final Logger log = LoggerFactory.getLogger(Account.class);


    public Account save(Account account) {

        Optional<Groupe> groupe = igroupeRepository.findById(account.getGroupe().getId());
        if (groupe.isPresent()) {
            account.setGroupe((groupe.get()));
        }
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
}
