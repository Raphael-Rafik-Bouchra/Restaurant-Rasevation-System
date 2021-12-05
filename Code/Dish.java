package dishes;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dish")
@XmlAccessorType(XmlAccessType.NONE)
public class Dish{

	@XmlElement(name = "type")
	private String type;
	@XmlElement(name = "name")
	private String name;
	@XmlElement(name = "price")
	private int price;
	private int taxes;

	
	
	public int getTaxes() {
		return taxes;
	}
	public void setTaxes(int taxes) {
		this.taxes = taxes;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Dish() {
		super();
	}

	
	
	
}
