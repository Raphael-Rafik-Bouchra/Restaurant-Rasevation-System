package dishes;

public class MainCourse extends Dish {

	private String type = "Main Course";
	private int taxes = 15;
	
	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int getTaxes() {
		return taxes;
	}

	@Override
	public void setTaxes(int taxes) {
		this.taxes = taxes;
	}
	
	
}
