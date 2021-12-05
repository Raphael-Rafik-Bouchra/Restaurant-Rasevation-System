package dishes;

public class Appetizer extends Dish {

	private String type = "Appetizer";
	private int taxes = 10;

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
