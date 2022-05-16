package app.igesa.payload.response;

import app.igesa.enumerations.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class JwtResponse {
	private String token;
	private Long id;
	private String username;
	private String email;
	private String fiscaleCode;
	private AccountStatus accountStatus;
	private List<String> roles;
	private Long groupeId ;

	public JwtResponse(String token, Long id, String username, String email, List<String> roles, String fiscaleCode, AccountStatus accountStatus,Long groupeId) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.fiscaleCode = fiscaleCode;
		this.groupeId=groupeId;
		this.accountStatus = accountStatus;

	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getFiscaleCode() {
		return fiscaleCode;
	}

	public void setFiscaleCode(String fiscaleCode) {
		this.fiscaleCode = fiscaleCode;
	}

	public Long getGroupeId() {
		return groupeId;
	}

	public void setGroupeId(Long groupeId) {
		this.groupeId = groupeId;
	}
}
