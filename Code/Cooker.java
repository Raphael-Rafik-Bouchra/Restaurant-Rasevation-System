package users;


public class Cooker extends Employee{

	private String role = "Cooker";

	@Override
	public String getRole() {
		return role;
	}

	@Override
	public void setRole(String role) {
		this.role = role;
	}


	
	
}
