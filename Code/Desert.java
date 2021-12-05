package dishes;

public class Desert extends Dish {
	
	private String type ="Desert";
	private int taxes = 20;
	
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
