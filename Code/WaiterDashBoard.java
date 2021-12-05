package gui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import users.Employee;
import users.Waiter;
import views.WaiterView;

public class WaiterDashBoard {
	private Waiter waiter = new Waiter();
	private Employee employee;
	private TableView reservationsTableView = new TableView();
	private ObservableList<WaiterView> clientsList = FXCollections.observableArrayList();
	private LoginForm loginFormScene;
	private Scene WaiterScene;
	private Stage stage;
	
	public WaiterDashBoard(Stage stage) {
		this.stage = stage;
	}
	
	public void prepareWaiterScene(){
		Label emptyLabel = new Label("         ");
		Label emptyLabel2 = new Label("         ");
		Label emptyLabel3 = new Label("         ");
		Label waiterNameLabel = new Label(" WELCOME " + waiter.getName());
		waiterNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Label waiterLabel = new Label("            (WAITER)");
		waiterLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

		Button logoutButton = new Button("Log Out");
		
		reservationsTableView.setPlaceholder(new Label("No reservations Today"));
		
		TableColumn<WaiterView , WaiterView> reservationsColumn   = new TableColumn<>("Reservations");
        TableColumn<WaiterView , Integer> tableNumberColumn  = new TableColumn<>("Table Number");
        tableNumberColumn.setMinWidth(135);
        tableNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        TableColumn<WaiterView , String> clientNameColumn   = new TableColumn<>("Client Name");
        clientNameColumn.setMinWidth(135);
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
		
        
		if(clientsList.size() != getClients().size()) { 
			clientsList = getClients();
		}
        reservationsTableView.setItems(clientsList);
        reservationsColumn.getColumns().add(tableNumberColumn);
        reservationsColumn.getColumns().add(clientNameColumn);
        reservationsTableView.getColumns().add(reservationsColumn);
        
        
EventHandler<ActionEvent> logoutEvent = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				reservationsTableView = new TableView<>();
				
				
				loginFormScene.prepareLoginForm();
				stage.setScene(loginFormScene.getLoginFormScene());
				
			}
		};
		
		logoutButton.setOnAction(logoutEvent);
		
        
        GridPane grid = new GridPane();
        grid.add(emptyLabel , 0 , 0);
        grid.add(waiterNameLabel, 1, 0);
        grid.add(waiterLabel, 1, 1);
        grid.add(emptyLabel2 , 0 , 2);
        grid.add(reservationsTableView, 1, 3);
        grid.add(emptyLabel3, 0, 4 );
        grid.add(logoutButton, 1, 5);
        
        WaiterScene = new Scene(grid, 345, 550);
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}
	
	public void setWaiter(String name , String username , String password) {
		this.waiter.setName(name);
		this.waiter.setUsername(username);
		this.waiter.setPassword(password);
	}
	
	private ObservableList<WaiterView> getClients(){
		ObservableList<WaiterView> clientss = FXCollections.observableArrayList();
		int i;
		for(i=0 ; i < employee.getReservingClients().size() ; i++) {
		clientss.add(new WaiterView(employee.getReservingClients().get(i).getClientTable().getTableNumber() , employee.getReservingClients().get(i).getName() ));
		}
		return clientss;
		
	}

	public LoginForm getLoginFormScene() {
		return loginFormScene;
	}

	public void setLoginFormScene(LoginForm loginFormScene) {
		this.loginFormScene = loginFormScene;
	}

	public Scene getWaiterScene() {
		return WaiterScene;
	}

	public void setWaiterScene(Scene waiterScene) {
		WaiterScene = waiterScene;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
}
