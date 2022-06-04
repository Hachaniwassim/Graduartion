package it.igesa.services;
import it.igesa.domaine.Account;
import it.igesa.enumerations.AccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Tarchoun Abir
 *
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String email;

	@JsonIgnore
	private String password;

	private String fiscaleCode ;

	@Getter
	@Setter
	private Long centerId;

	private Long groupeId;

	private AccountStatus accountStatus;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String username, String email, String password, String fiscaleCode ,Long id1, AccountStatus accountStatus, List<GrantedAuthority> authorities,Long groupeId) {
		this.id=id;
		this.username=username;
		this.email=email;
		this.password=password;
		this.fiscaleCode=fiscaleCode;
		this.accountStatus=accountStatus;
		this.authorities=authorities;
		this.centerId=id1;
		this.groupeId=groupeId;
	}


	public static UserDetailsImpl build(Account account) {
		List<GrantedAuthority> authorities = account.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());
		return new UserDetailsImpl(
				account.getId(),
				account.getUsername(),
				account.getEmail(),
				account.getPassword(),
				account.getFiscaleCode(),
				account.getGroupe().getId(),
				account.getAccountStatus(),
				authorities,
				account.getGroupe().getId()
		);
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}


	public AccountStatus getAccountStatus() {
		return accountStatus;
	}


	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}


	public String getFiscaleCode() {
		return fiscaleCode;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}



}
