package views;

public class WaiterView {

	private int tableNumber;
	private String clientName;
	
	
	
	public WaiterView(int tableNumber, String clientName) {
		super();
		this.tableNumber = tableNumber;
		this.clientName = clientName;
	}
	public int getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	
	
}
