package gui;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.stage.Stage;
import restaurant.Restaurant;
import users.Employee;

public class MainController extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		LoginForm login = new LoginForm(primaryStage);
		ClientDashBoard clientScene = new ClientDashBoard(primaryStage);
		WaiterDashBoard waiterScene = new WaiterDashBoard(primaryStage);
		CookerDashBoard cookerScene = new CookerDashBoard(primaryStage);
		ManagerDashBoard managerScene = new ManagerDashBoard(primaryStage);
		
		JAXBContext inputData = JAXBContext.newInstance(Restaurant.class);
		Unmarshaller inputDataUnmarshaller = inputData.createUnmarshaller();
		Restaurant restaurant = (Restaurant) inputDataUnmarshaller.unmarshal(new File ("data.xml"));
		
		JAXBContext ordersData = JAXBContext.newInstance(Employee.class);
		Unmarshaller orderDataUnmarshaller = ordersData.createUnmarshaller();
		Employee employee = new Employee();
		try {
		employee = (Employee) orderDataUnmarshaller.unmarshal(new File ("orderData.xml"));
		}
		catch (javax.xml.bind.UnmarshalException e) {
			Marshaller orderDataMarshaller = ordersData.createMarshaller();
			orderDataMarshaller.marshal(employee, new File ("orderData.xml"));
		}
		
		login.setEmployee(employee);
		
		primaryStage.setTitle(restaurant.getRestaurantName() + " Restaurant");
		
		login.setRestaurant(restaurant);
		login.prepareLoginForm();
		
		
		clientScene.setLoginFormScene(login);
		waiterScene.setLoginFormScene(login);
		cookerScene.setLoginFormScene(login);
		managerScene.setLoginFormScene(login);
		login.setClientScene(clientScene);
		login.setWaiterScene(waiterScene);
		login.setCookerScene(cookerScene);
		login.setManagerScene(managerScene);
		
		primaryStage.setScene(login.getLoginFormScene());
		primaryStage.show();
	}

}
