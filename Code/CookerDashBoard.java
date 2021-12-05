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
import users.Cooker;
import users.Employee;
import views.CookerView;

public class CookerDashBoard {
	private Cooker cooker = new Cooker();
	private Employee employee;
	private TableView reservationsTableView = new TableView();
	private ObservableList<CookerView> clientsList = FXCollections.observableArrayList();
	private LoginForm loginFormScene;
	private Scene CookerScene;
	private Stage stage;
	
	public CookerDashBoard(Stage stage) {
		this.stage = stage;
	}
	
	public void prepareCookerScene(){
		Label emptyLabel = new Label("         ");
		Label emptyLabel2 = new Label("         ");
		Label emptyLabel3 = new Label("         ");
		Label cookerNameLabel = new Label(" WELCOME " + cooker.getName());
		cookerNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Label cookerLabel = new Label("            (COOKER)");
		cookerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

		Button logoutButton = new Button("Log Out");
		
		reservationsTableView.setPlaceholder(new Label("No reservations Today"));
		
		TableColumn<CookerView , CookerView> reservationsColumn   = new TableColumn<>("Reservations");
        TableColumn<CookerView , Integer> tableNumberColumn  = new TableColumn<>("Table Number");
        tableNumberColumn.setMinWidth(137);
        tableNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
		
        TableColumn<CookerView , String> nameColumn   = new TableColumn<>("Dish");
        nameColumn.setMinWidth(137);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        
		if(clientsList.size() != getClients().size()) { 
			clientsList = getClients();
		}
        reservationsTableView.setItems(clientsList);
        reservationsColumn.getColumns().add(tableNumberColumn);
        reservationsColumn.getColumns().add(nameColumn);
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
        grid.add(cookerNameLabel, 1, 0);
        grid.add(cookerLabel, 1, 1);
        grid.add(emptyLabel2 , 0 , 2);
        grid.add(reservationsTableView, 1, 3);
        grid.add(emptyLabel3, 0, 4 );
        grid.add(logoutButton, 1, 5);
        
        CookerScene = new Scene(grid, 345, 550);
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Cooker getCooker() {
		return cooker;
	}

	public void setCooker(Cooker cooker) {
		this.cooker = cooker;
	}
	
	public void setCooker(String name , String username , String password) {
		this.cooker.setName(name);
		this.cooker.setUsername(username);
		this.cooker.setPassword(password);
	}
	
	private ObservableList<CookerView> getClients(){
		ObservableList<CookerView> clientss = FXCollections.observableArrayList();
		int i , j;
		for(i=0 ; i < employee.getReservingClients().size() ; i++) {
			for(j=0 ; j < employee.getReservingClients().get(i).getClientOrder().getDishes().size() ; j++) {
				clientss.add(new CookerView(employee.getReservingClients().get(i).getClientTable().getTableNumber() , employee.getReservingClients().get(i).getClientOrder().getDishes().get(j).getName()));
			}
		}
		return clientss;
		
	}

	public LoginForm getLoginFormScene() {
		return loginFormScene;
	}

	public void setLoginFormScene(LoginForm loginFormScene) {
		this.loginFormScene = loginFormScene;
	}

	public Scene getCookerScene() {
		return CookerScene;
	}

	public void setCookerScene(Scene cookerScene) {
		CookerScene = cookerScene;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
}
