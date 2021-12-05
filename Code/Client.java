package users;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import order.Order;
import tables.Table;
import tables.Tables;

@XmlRootElement(name = "Client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client extends User {
	private String role = "Client";
	@XmlElement(name = "clientTable")
	private Table clientTable = null;
	@XmlElement(name = "clientOrder")
	private Order clientOrder = null;
	@XmlElement(name = "clientIsReserving")
	private boolean reserving = false;

	@Override
	public String getRole() {
		return role;
	}

	@Override
	public void setRole(String role) {
		this.role = role;
	}

	
	
	public Table getClientTable() {
		return clientTable;
	}

	public void setClientTable(Table clientTable) {
		this.clientTable = clientTable;
	}
	
	

	public Order getClientOrder() {
		return clientOrder;
	}

	public void setClientOrder(Order clientOrder) {
		this.clientOrder = clientOrder;
	}
	
	

	public boolean isReserving() {
		return reserving;
	}

	public void setReserving(boolean reserving) {
		this.reserving = reserving;
	}

	public int reserveTable(Tables tables , List<Client> reservingClients , int seats , boolean smoking) {
		int i , j , k , reservingTableNumber = -1;
		setReserving(false);
	
		for(i=0 ; i < tables.getTables().size() ; i++) {
			if(tables.getTables().get(i).getSeats() == seats) 
				if(tables.getTables().get(i).isSmoking() == smoking) {
					reservingTableNumber = tables.getTables().get(i).getTableNumber();
					break;
				}
		}
		
		for(j=0 ; j < reservingClients.size() ; j++) {
			if(reservingClients.get(j).getClientTable().getTableNumber() == reservingTableNumber) {
				return -1;
			}
		}
		
		for(k = 0 ; k < tables.getTables().size() ; k++) {
			if(tables.getTables().get(k).getSeats() == seats) 
				if(tables.getTables().get(k).isSmoking() == smoking) {
					setReserving(true);
					setClientTable(tables.getTables().get(k));
					break;
				}
		}
		
		if(isReserving() == true)
			return 1;
		else
			return 0;
		
		
	}
	
	
}
