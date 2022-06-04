package it.igesa.payload.response;

import it.igesa.enumerations.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor

public class JwtResponse {
	@Getter
	@Setter
	private String token;
	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private String username;
	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	private String fiscaleCode;
	@Getter
	@Setter
	private AccountStatus accountStatus;
	@Getter
	@Setter
	private List<String> roles;
	@Getter
	@Setter
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


	}

