package order;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import dishes.Dish;
@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order{

	@XmlElement(name = "totalPrice")
	private int totalPrice = 0;
	@XmlElement(name = "totalTaxes")
	private double totalTaxes = 0;
	@XmlElement(name = "totalCost")
	private double totalCost = 0;
	@XmlElement(name = "orderDishes")
	private List<Dish> dishes = new ArrayList<Dish>();
	
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getTotalTaxes() {
		return totalTaxes;
	}
	public void setTotalTaxes(double totalTaxes) {
		this.totalTaxes = totalTaxes;
	}
	

	public List<Dish> getDishes() {
		return dishes;
	}
	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	public void makeCalculations(List<Dish> dishes) {
		int i;
		for(i=0 ; i < dishes.size() ; i++) {
			setTotalPrice((dishes.get(i).getPrice()) + getTotalPrice());
			setTotalTaxes((dishes.get(i).getTaxes()*dishes.get(i).getPrice()*(1.0)/100) + getTotalTaxes());
			setTotalCost(getTotalPrice()+getTotalTaxes());
		}
	}
	
	
	
}
