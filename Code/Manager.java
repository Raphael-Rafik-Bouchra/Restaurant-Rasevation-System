package users;


public class Manager extends Employee {

	private String role = "Manager";
	private int totalEarnedMoney=0;
	
	



	public int getTotalEarnedMoney() {
		return totalEarnedMoney;
	}

	public void setTotalEarnedMoney(int totalEarnedMoney) {
		this.totalEarnedMoney = totalEarnedMoney;
	}

	@Override
	public String getRole() {
		return role;
	}

	@Override
	public void setRole(String role) {
		this.role = role;
	}

	public void makeCalculations() {
		int i;
		for(i=0 ; i < getReservingClients().size() ; i++) {
			Client client = new Client();
			client = (Client) getReservingClients().get(i);
			setTotalEarnedMoney(client.getClientOrder().getTotalPrice() + getTotalEarnedMoney());
		}
	}

	
	
}
