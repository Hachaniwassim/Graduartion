package app.igesa.payload.request;


public class LoginRequest {

	private String username;


	private String password;
	//private String recaptchaResponse;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
