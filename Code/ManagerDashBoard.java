package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import users.Employee;
import users.Manager;

public class ManagerDashBoard {
	private Employee employee;
	private Manager manager = new Manager();
	private LoginForm loginFormScene;
	private Scene ManagerScene;
	private Stage stage;
	
	public ManagerDashBoard(Stage stage) {
		this.stage = stage;
	}

	public void prepareManagerScene() {
		Label managerNameLabel = new Label("          WELCOME " + manager.getName());
		managerNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Label managerLabel = new Label("                 (MANAGER)");
		managerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Label reservingsLabel = new Label("                  Reservings");
		reservingsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

		Button logoutButton = new Button("Log Out");
		
		manager.setReservingClients(employee.getReservingClients());
		
		VBox vbox = new VBox();
		
		int i , j;
		for(i=0 ; i < employee.getReservingClients().size() ; i++) {
			vbox.getChildren().add(new Label(" Table Number: " + employee.getReservingClients().get(i).getClientTable().getTableNumber()));
			vbox.getChildren().add(new Label(" Client Name: " + employee.getReservingClients().get(i).getName()));
			vbox.getChildren().add(new Label(" Ordered Dishes:"));
			for(j=0 ; j < employee.getReservingClients().get(i).getClientOrder().getDishes().size() ; j++)
				vbox.getChildren().add(new Label(" + " + employee.getReservingClients().get(i).getClientOrder().getDishes().get(j).getName()));
			vbox.getChildren().add(new Label(" Total Price: " + employee.getReservingClients().get(i).getClientOrder().getTotalPrice() + " (EGP)"));
			vbox.getChildren().add(new Label(" Total Taxes: " + employee.getReservingClients().get(i).getClientOrder().getTotalTaxes() + " (EGP)"));
			vbox.getChildren().add(new Label(" Total Cost: " + employee.getReservingClients().get(i).getClientOrder().getTotalCost() + " (EGP)"));

			vbox.getChildren().add(new Label(" "));
			vbox.getChildren().add(new Label(" "));
		}
		
		manager.makeCalculations();
		Label totalMoneyLabel = new Label(" Total Earned Money = " + manager.getTotalEarnedMoney() + " (EGP)");
		totalMoneyLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		
EventHandler<ActionEvent> logoutEvent = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				manager = new Manager();
				loginFormScene.prepareLoginForm();
				stage.setScene(loginFormScene.getLoginFormScene());
				
			}
		};
		
		logoutButton.setOnAction(logoutEvent);
		
		GridPane grid = new GridPane();
		grid.add(managerNameLabel, 0, 0);
		grid.add(managerLabel, 0, 1);
		grid.add(new Label("  "), 0, 2);
		grid.add(reservingsLabel, 0, 3);
		grid.add(vbox, 0, 4);
		grid.add(totalMoneyLabel, 0, 5);
		grid.add(new Label("  "), 0, 6);
		grid.add(logoutButton, 0, 7);
		
		ManagerScene = new Scene(grid, 345, 750);
	}

	public LoginForm getLoginFormScene() {
		return loginFormScene;
	}

	public void setLoginFormScene(LoginForm loginFormScene) {
		this.loginFormScene = loginFormScene;
	}

	public Scene getManagerScene() {
		return ManagerScene;
	}

	public void setManagerScene(Scene managerScene) {
		ManagerScene = managerScene;
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
	public void setManager(String name , String username , String password) {
		this.manager.setName(name);
		this.manager.setUsername(username);
		this.manager.setPassword(password);
	}
	
}
