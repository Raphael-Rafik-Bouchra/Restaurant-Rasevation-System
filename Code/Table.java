package tables;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "table")
@XmlAccessorType(XmlAccessType.FIELD)

public class Table{
	@XmlElement(name = "number")
	private int tableNumber;
	@XmlElement(name = "number_of_seats")
	private int seats;
	@XmlElement(name = "smoking")
	private boolean smoking;

	
	public Table(int tableNumber, int seats, boolean smoking) {
		super();
		this.tableNumber = tableNumber;
		this.seats = seats;
		this.smoking = smoking;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public boolean isSmoking() {
		return smoking;
	}

	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}


	public Table() {
		super();
	}


	
	
	

}
