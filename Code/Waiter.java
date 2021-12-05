package users;


public class Waiter extends Employee {

	private String role = "Waiter";

	@Override
	public String getRole() {
		return role;
	}

	@Override
	public void setRole(String role) {
		this.role = role;
	}
	
}
