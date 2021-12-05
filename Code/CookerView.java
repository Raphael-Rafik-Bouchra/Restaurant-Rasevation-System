package views;

public class CookerView {
	private int tableNumber;
	private String dishName;
	public CookerView(int tableNumber,String dishName) {
		super();
		this.tableNumber = tableNumber;
		this.dishName = dishName;
	}
	public int getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	
}
