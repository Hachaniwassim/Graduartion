package it.igesa.repository;
import it.igesa.domaine.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
/**
 * @author Tarchoun Abir
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


	Optional<Account> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
    public Account findByEmail(String email);

	//for update
	@Modifying
	@Transactional
	@Query(value="update accounts a set groupe_id= :groupe_id where a.id = :id",nativeQuery = true)
	public void assignGroupe(@Param("groupe_id") Long groupe_id, @Param("id") Long id);


	@Modifying
	@Transactional
	@Query(value="update accounts a set  username= :username, email= :email , fiscale_code=:fiscale_code where a.id = :id",nativeQuery = true)
	void updateCUrrentUser(@Param("username") String username, @Param("email") String email, @Param("fiscale_code") String fiscale_code,@Param("id")Long id);

	//List<Account> findByEntrepriseId(Long id);
}
