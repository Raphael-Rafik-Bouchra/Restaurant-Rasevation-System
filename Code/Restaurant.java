package restaurant;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import dishes.Dishes;
import tables.Tables;
import users.Users;

@XmlRootElement(name = "restaurant")
@XmlAccessorType(XmlAccessType.NONE)
public class Restaurant {
	private String restaurantName = "R&Y";
	
	@XmlElement(name = "users")
	private Users user;
	
	@XmlElement(name = "tables")
	private Tables tables;

	@XmlElement(name = "dishes")
	private Dishes dishes;
	
	
	
	public String getRestaurantName() {
		return restaurantName;
	}



	public Users getUser() {
		return user;
	}



	public void setUser(Users user) {
		this.user = user;
	}



	public Tables getTables() {
		return tables;
	}



	public void setTables(Tables tables) {
		this.tables = tables;
	}



	public Dishes getDishes() {
		return dishes;
	}



	public void setDishes(Dishes dishes) {
		this.dishes = dishes;
	}



	public Restaurant() {
		super();
	}




	
	
	

	
}
