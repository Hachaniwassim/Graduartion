package it.igesa.repository;
import it.igesa.domaine.Role;
import it.igesa.enumerations.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 * @author Tarchoun.Abir
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);

	@Modifying
	@Transactional
	@Query(value="update account_roles a set  role_id= :role_id where a.account_id = :account_id",nativeQuery = true)
	void updateRoleQuerry(@Param("role_id") Long role_id, @Param("account_id") Long account_id);

}
