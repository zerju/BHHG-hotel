package beani;

public class Login {

	
	private String username;
	private String password;
	
	public Login(){}
	
	public Login (Login user){
		this.username=user.getUsername();
		this.password=user.getPassword();
	}
	
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
