package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import restaurant.Restaurant;
import users.Employee;
//import users.Client;

public class LoginForm {
	private Restaurant restaurant;
	private Employee employee;
	private ClientDashBoard clientScene;
	private ManagerDashBoard managerScene;
	private WaiterDashBoard waiterScene;
	private CookerDashBoard cookerScene;
	private Scene LoginFormScene;
	private Stage stage;
	
	public LoginForm(Stage stage) {
		this.stage = stage;
	}
	public void prepareLoginForm() {
		Label emptyLabel1 = new Label("    ");
		Label emptyLabel2 = new Label("    ");
		Label usernameLabel = new Label(" Username\t");
		Label passwordLabel = new Label(" Password\t");
		Label validationLabel = new Label();
		TextField usernameField = new TextField();
		PasswordField passwordField = new PasswordField();
		
		
		Button loginButton = new Button("Login");
		
		GridPane grid = new GridPane();
		grid.add(emptyLabel1, 0, 0);
		grid.add(usernameLabel, 0, 1);
		grid.add(usernameField, 1, 1);
		grid.add(passwordLabel, 0, 2);
		grid.add(passwordField, 1, 2);
		grid.add(emptyLabel2, 0, 3);
		grid.add(loginButton, 1, 4);
		grid.add(validationLabel, 1, 5);
		
		
		
		EventHandler<ActionEvent> loginEvent = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				int i;
				boolean userFound = false;
				for(i=0 ; i < restaurant.getUser().getUsers().size(); i++) {
					System.out.println();
					if(restaurant.getUser().getUsers().get(i).getUsername().equals(usernameField.getText())) {
						if(restaurant.getUser().getUsers().get(i).getPassword().equals(passwordField.getText())) {
							userFound = true;
							if(restaurant.getUser().getUsers().get(i).getRole().equals("Client")) {
								clientScene.setRestaurant(getRestaurant());
								clientScene.setEmployee(employee);
								clientScene.setClient(restaurant.getUser().getUsers().get(i).getName(), restaurant.getUser().getUsers().get(i).getUsername(), restaurant.getUser().getUsers().get(i).getPassword());
								clientScene.prepareClientScene();
								stage.setScene(clientScene.getClientScene());
							}
							else if(restaurant.getUser().getUsers().get(i).getRole().equals("Manager")) {
								managerScene.setEmployee(employee);
								managerScene.setManager(restaurant.getUser().getUsers().get(i).getName(), restaurant.getUser().getUsers().get(i).getUsername(), restaurant.getUser().getUsers().get(i).getPassword());
								managerScene.prepareManagerScene();
								stage.setScene(managerScene.getManagerScene());
							}
							else if(restaurant.getUser().getUsers().get(i).getRole().equals("Waiter")) {
								waiterScene.setEmployee(employee);
								waiterScene.setWaiter(restaurant.getUser().getUsers().get(i).getName(), restaurant.getUser().getUsers().get(i).getUsername(), restaurant.getUser().getUsers().get(i).getPassword());
								waiterScene.prepareWaiterScene();
								stage.setScene(waiterScene.getWaiterScene());
							}
							else //Cooker
							{
								cookerScene.setEmployee(employee);
								cookerScene.setCooker(restaurant.getUser().getUsers().get(i).getName(), restaurant.getUser().getUsers().get(i).getUsername(), restaurant.getUser().getUsers().get(i).getPassword());
								cookerScene.prepareCookerScene();
								stage.setScene(cookerScene.getCookerScene());
							}
						}
					}
				}
				if(userFound == false)
					validationLabel.setText("Invalid Username or Password");
				
			}
		};
		
		loginButton.setOnAction(loginEvent);
		
		LoginFormScene = new Scene(grid, 320, 200);
	}




	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public ClientDashBoard getClientScene() {
		return clientScene;
	}


	public void setClientScene(ClientDashBoard clientScene) {
		this.clientScene = clientScene;
	}


	public ManagerDashBoard getManagerScene() {
		return managerScene;
	}


	public void setManagerScene(ManagerDashBoard managerScene) {
		this.managerScene = managerScene;
	}


	public WaiterDashBoard getWaiterScene() {
		return waiterScene;
	}


	public void setWaiterScene(WaiterDashBoard waiterScene) {
		this.waiterScene = waiterScene;
	}


	public CookerDashBoard getCookerScene() {
		return cookerScene;
	}


	public void setCookerScene(CookerDashBoard cookerScene) {
		this.cookerScene = cookerScene;
	}


	public Scene getLoginFormScene() {
		return LoginFormScene;
	}


	public void setLoginFormScene(Scene loginFormScene) {
		LoginFormScene = loginFormScene;
	}
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
	
	
}
