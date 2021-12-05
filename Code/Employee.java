package users;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "reservingClients")
@XmlAccessorType(XmlAccessType.NONE)
public class Employee extends User {

	private String role = "Employee";
	@XmlElement(name = "reservingClient")
	private List<Client>  reservingClients = new ArrayList<Client>();

	@Override
	public String getRole() {
		return role;
	}

	@Override
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	public List<Client> getReservingClients() {
		return reservingClients;
	}

	public void setReservingClients(List<Client> reservingClients) {
		this.reservingClients = reservingClients;
	}

	public void seeClients(List<User> users) {
		int i;
		Client client = new Client();
		for(i=0 ; i<users.size() ; i++) {
		if(users.get(i).getRole() == "Client") {
			client = (Client) users.get(i);
			if(client.isReserving() == true) {
				getReservingClients().add(client);
			}
		}
		}
	}
}
