package app.igesa.metiers;

import app.igesa.entity.Account;
import app.igesa.enumerations.AccountStatus;

import java.util.List;
import java.util.Optional;

public interface Iaccount {
    public Account updateSatus(Long id, AccountStatus status);
    Account save(Account account);
    public Optional<Account> findById(Long id);
    public List<Account> findAll();
    public void delete(Long id);
}
