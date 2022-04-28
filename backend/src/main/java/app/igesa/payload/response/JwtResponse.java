package app.igesa.payload.response;

import app.igesa.entity.Groupe;
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
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private String codefiscale;
	//private String password;
	private List<String> roles;
	//private Groupe groupe ;

	public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles,String codefiscale) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.codefiscale =codefiscale;
		//this.groupe=groupe;

	}


	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
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

	public String getCodefiscale() {
		return codefiscale;
	}

	public void setCodefiscale(String codefiscale) {
		this.codefiscale = codefiscale;
	}
}
