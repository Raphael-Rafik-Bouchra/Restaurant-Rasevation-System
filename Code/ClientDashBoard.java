package gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.TabExpander;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import dishes.Appetizer;
import dishes.Desert;
import dishes.Dish;
import dishes.MainCourse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import order.Order;
import restaurant.Restaurant;
import users.Client;
import users.Employee;

public class ClientDashBoard {

	private Restaurant restaurant;
	private Client client = new Client();
	private Employee employee = new Employee();
	private	List<Appetizer> appetizers = new ArrayList<Appetizer>();
    private List<MainCourse> mainCourses = new ArrayList<MainCourse>();
    private List<Desert> deserts = new ArrayList<Desert>();
	private LoginForm loginFormScene;
	private Scene ClientScene;
	private Stage stage;
	private TableView appetizersTableView = new TableView();
    private TableView mainCourseTableView = new TableView();
    private TableView desertTableView = new TableView();
	private boolean passed = false;
	private ObservableList<Appetizer> appetizerList = FXCollections.observableArrayList();
	private ObservableList<MainCourse> mainCourseList = FXCollections.observableArrayList();
	private ObservableList<Desert> desertList = FXCollections.observableArrayList();
	
	public ClientDashBoard(Stage stage) {
		this.stage = stage;
	}
	
	public void prepareClientScene() {
		Label clientNameLabel = new Label(" WELCOME " + client.getName());
		clientNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Label clientLabel = new Label("        (CLIENT)");
		clientLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		Label reserveTableLabel = new Label(" Reserve a table:\t");
		Label seatsNumberLabel = new Label (" Seats Number\t");
		Label smokingLabel = new Label(" Smoking\t");
		Label validatingTableLabel = new Label();
		Label TableNumberLabel = new Label();
		Label menuLabel = new Label();
		Label addDishLabel = new Label();
		Label addDishValidationLabel = new Label();
		Label checkoutLabel = new Label();
		Label checkoutValidationLabel = new Label();
		Label totalPriceLabel = new Label();
		Label totalTaxesLabel = new Label();
		Label totalCostLabel = new Label();
		Label saveValidationLabel = new Label();
		
		final Spinner<Integer> seatsNumberSpinner = new Spinner<Integer>();
        final int initialValue = 1;
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, initialValue);
        seatsNumberSpinner.setValueFactory(valueFactory);
        
        RadioButton yesRadioButton = new RadioButton("Yes");
        RadioButton noRadioButton = new RadioButton("No");
        
        ToggleGroup radioGroup = new ToggleGroup();
        yesRadioButton.setToggleGroup(radioGroup);
        noRadioButton.setToggleGroup(radioGroup);
        
        Button reserveTableButton = new Button("Reseve Table");
        Button addDishButton = new Button("Add Dish");
        addDishButton.setDisable(true);
        Button checkoutButton = new Button("Checkout");
        checkoutButton.setDisable(true);
        Button saveButton = new Button("Save");
        saveButton.setDisable(true);
        Button logoutButton = new Button("Log Out");
        

        
        int i;
        for(i=0 ; i < restaurant.getDishes().getDishes().size() ; i++) {
        	if(restaurant.getDishes().getDishes().get(i).getType().equals("appetizer")) {
        		Appetizer appetizer = new Appetizer();
        		appetizer.setName(restaurant.getDishes().getDishes().get(i).getName());
        		appetizer.setPrice(restaurant.getDishes().getDishes().get(i).getPrice());
        		appetizers.add(appetizer);
        	}
        	else if(restaurant.getDishes().getDishes().get(i).getType().equals("main_course")) {
        		MainCourse mainCourse = new MainCourse();
        		mainCourse.setName(restaurant.getDishes().getDishes().get(i).getName());
        		mainCourse.setPrice(restaurant.getDishes().getDishes().get(i).getPrice());
        		mainCourses.add(mainCourse);
        	}
        	else //Desert
        	{
        		Desert desert = new Desert();
        		desert.setName(restaurant.getDishes().getDishes().get(i).getName());
        		desert.setPrice(restaurant.getDishes().getDishes().get(i).getPrice());
        		deserts.add(desert);
        	}
        }
        
       
        appetizersTableView.setPlaceholder(new Label("Reserve a table to show MENU"));
        mainCourseTableView.setPlaceholder(new Label("Reserve a table to show MENU"));
        desertTableView.setPlaceholder(new Label("Reserve a table to show MENU"));

        EventHandler<ActionEvent> reserveTableEvent = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				int reservingTable = 0;
				RadioButton selectedRadioButton =(RadioButton) radioGroup.getSelectedToggle();
				try {
				if(selectedRadioButton.getText().equals("Yes"))
					reservingTable = client.reserveTable(restaurant.getTables(), employee.getReservingClients(), seatsNumberSpinner.getValue(), true);
				else if(selectedRadioButton.getText().equals("No"))
					reservingTable = client.reserveTable(restaurant.getTables(), employee.getReservingClients(), seatsNumberSpinner.getValue(), false);
				
				if(reservingTable == 1) {
					reserveTableButton.setDisable(true);
					validatingTableLabel.setText(" Table Successfuly Reserved");
					TableNumberLabel.setText(" Table Number:" + client.getClientTable().getTableNumber());
					TableNumberLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
					
					
					menuLabel.setText("\t     MENU");
					menuLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					
					//Appetizers Tabelview
					
			        TableColumn<Dish , Dish> appetizerNameColumn   = new TableColumn<>("Apetizers (10% Taxes)");
			        TableColumn<Dish , String> nameColumn  = new TableColumn<>("Dish");
			        nameColumn.setMinWidth(125);
			        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			        TableColumn<Dish , Integer> priceColumn   = new TableColumn<>("Price (EGP)");
			        priceColumn.setMinWidth(125);
			        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
					
			        
					if(passed == false) 
						appetizerList = getAppetizers();
					
			        appetizersTableView.setItems(appetizerList);
			        appetizerNameColumn.getColumns().add(nameColumn);
			        appetizerNameColumn.getColumns().add(priceColumn);
			        appetizersTableView.getColumns().add(appetizerNameColumn);
			        
			        //Main Course Tableview
			        TableColumn<Dish , Dish> mainCourseNameColumn   = new TableColumn<>("Main Courses (15% Taxes)");
			        TableColumn<Dish , String> nameColumn2  = new TableColumn<>("Dish");
			        nameColumn2.setMinWidth(125);
			        nameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
			        TableColumn<Dish , Integer> priceColumn2   = new TableColumn<>("Price (EGP)");
			        priceColumn2.setMinWidth(125);
			        priceColumn2.setCellValueFactory(new PropertyValueFactory<>("price"));
			        
			        if(passed == false)
			        	mainCourseList = getMainCourses();
			        mainCourseTableView.setItems(mainCourseList);
			        mainCourseNameColumn.getColumns().add(nameColumn2);
			        mainCourseNameColumn.getColumns().add(priceColumn2);
			        mainCourseTableView.getColumns().add(mainCourseNameColumn);
			        
			        //Desert Tableview
			        TableColumn<Desert , Desert> desertNameColumn   = new TableColumn<>("Desert (20% Taxes)");
			        TableColumn<Desert , String> nameColumn3  = new TableColumn<>("Dish");
			        nameColumn3.setMinWidth(125);
			        nameColumn3.setCellValueFactory(new PropertyValueFactory<>("name"));
			        TableColumn<Desert , Integer> priceColumn3   = new TableColumn<>("Price (EGP)");
			        priceColumn3.setMinWidth(125);
			        priceColumn3.setCellValueFactory(new PropertyValueFactory<>("price"));
			        
			        if(passed == false)
			        	desertList = getDeserts();
			        desertTableView.setItems(desertList);
			        desertNameColumn.getColumns().add(nameColumn3);
			        desertNameColumn.getColumns().add(priceColumn3);
			        desertTableView.getColumns().add(desertNameColumn);
			        
			        //Select Dishes
			        addDishButton.setDisable(false);
			        checkoutButton.setDisable(false);
			        addDishLabel.setText(" Select a dish and press Add Dish:");
			        checkoutLabel.setText(" To make order press checkout:");
			        TableViewSelectionModel<Appetizer> appetizersSelectionModel = appetizersTableView.getSelectionModel();
			        appetizersSelectionModel.setSelectionMode(SelectionMode.SINGLE);
			        
			        TableViewSelectionModel<MainCourse> mainCourseSelectionModel = mainCourseTableView.getSelectionModel();
			        mainCourseSelectionModel.setSelectionMode(SelectionMode.SINGLE);
			        
			        TableViewSelectionModel<Desert> desertSelectionModel = desertTableView.getSelectionModel();
			        desertSelectionModel.setSelectionMode(SelectionMode.SINGLE);
			        
			        List<Dish> orderDishes = new ArrayList<Dish>();
			        
			        EventHandler<ActionEvent> addDishEvent = new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							boolean dishSelected = false;
							checkoutValidationLabel.setText("");
							if(appetizersSelectionModel.getSelectedItem() != null) {
							dishSelected = true;	
							orderDishes.add(appetizersSelectionModel.getSelectedItem());
							appetizersSelectionModel.clearSelection();
							}
							if(mainCourseSelectionModel.getSelectedItem() != null) {
							dishSelected = true;	
							orderDishes.add(mainCourseSelectionModel.getSelectedItem());
							mainCourseSelectionModel.clearSelection();
							}
							if(desertSelectionModel.getSelectedItem() != null) {
							dishSelected = true;
							orderDishes.add(desertSelectionModel.getSelectedItem());
							desertSelectionModel.clearSelection();
							}
							if(dishSelected == true)
								addDishValidationLabel.setText("Dish added successfuly");
							else
								addDishValidationLabel.setText("Please Select a dish");
						}
					};
					
			        addDishButton.setOnAction(addDishEvent);
			        
			        Order clientorder = new Order();
			        EventHandler<ActionEvent> checkoutEvent = new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							if(orderDishes.size() != 0) {
							checkoutValidationLabel.setText("");
							saveButton.setDisable(false);
							clientorder.setDishes(orderDishes);
							clientorder.makeCalculations(clientorder.getDishes());
							totalPriceLabel.setText(" Total Price = " + clientorder.getTotalPrice() + " EGP");
							totalTaxesLabel.setText(" Total Taxes = " + clientorder.getTotalTaxes() + " EGP");
							totalCostLabel.setText(" Total Cost = " + clientorder.getTotalCost() + " EGP");
							totalCostLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
							
							}
							else {
								checkoutValidationLabel.setText("Please select order dihes");
							}
							
						}
					};
			        checkoutButton.setOnAction(checkoutEvent);
			        
			        EventHandler<ActionEvent> saveEvent = new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							client.setClientOrder(clientorder);
							employee.getReservingClients().add(client);
							try {
								JAXBContext ordersData2 = JAXBContext.newInstance(Employee.class);
								Marshaller orderDataMarshaller = ordersData2.createMarshaller();
								orderDataMarshaller.marshal(employee, new File ("orderData.xml"));
								saveValidationLabel.setText(" Saved Successfuly");
								setClient(new Client());
								
							} catch (JAXBException e) {
								saveValidationLabel.setText(" Saving Failed");
							}
							
							
						}
					};
					
					saveButton.setOnAction(saveEvent);
			        
				}
				else if(reservingTable == -1) {
					validatingTableLabel.setText(" Table already reserved");
				}
				else
					validatingTableLabel.setText(" Sorry no table available");
				}
				catch (NullPointerException e) {
					validatingTableLabel.setText(" Please Enter Smoking Value");
					
				}
			}
		};
		
		
		EventHandler<ActionEvent> logoutEvent = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				passed = true;
				appetizersTableView = new TableView<>();
				mainCourseTableView = new TableView<>();
				desertTableView = new TableView<>();
				
				loginFormScene.prepareLoginForm();
				stage.setScene(loginFormScene.getLoginFormScene());
				
			}
		};
		
		logoutButton.setOnAction(logoutEvent);
		
	     GridPane grid = new GridPane();
	        grid.add(clientNameLabel, 1, 0);
	        grid.add(clientLabel, 1, 1);
	        grid.add(reserveTableLabel , 0 ,2);
	        grid.add(seatsNumberLabel,0, 3);
	        grid.add(seatsNumberSpinner, 1, 3);
	        grid.add(smokingLabel, 0, 4);
	        grid.add(yesRadioButton, 1, 4);
	        grid.add(noRadioButton, 1, 5);
	        grid.add(reserveTableButton, 0, 6);
	        grid.add(validatingTableLabel, 0, 7);
	        grid.add(TableNumberLabel, 0, 8);
	        grid.add(menuLabel, 1, 9);
	        grid.add(appetizersTableView, 0, 10);
	        grid.add(mainCourseTableView, 1, 10);
	        grid.add(desertTableView, 2, 10);
	        grid.add(addDishLabel, 0, 11);
	        grid.add(addDishButton, 1, 11);
	        grid.add(addDishValidationLabel, 2, 11);
	        grid.add(checkoutLabel, 0, 12);
	        grid.add(checkoutButton, 1, 12);
	        grid.add(checkoutValidationLabel, 2, 12);
	        grid.add(totalPriceLabel, 0, 13);
	        grid.add(totalTaxesLabel, 0, 14);
	        grid.add(totalCostLabel, 0, 15);
	        grid.add(saveButton, 1, 16);
	        grid.add(saveValidationLabel, 0, 16);
	        grid.add(logoutButton, 2, 16);
	        
	        
        reserveTableButton.setOnAction(reserveTableEvent);

        ClientScene = new Scene(grid, 756, 900);
	}
	
	private ObservableList<Appetizer> getAppetizers(){
		ObservableList<Appetizer> appetizerss = FXCollections.observableArrayList();
		int i;
		for(i=0 ; i < appetizers.size() ; i++) {
		appetizerss.add(appetizers.get(i));
		}
		return appetizerss;
		
	}
	private ObservableList<MainCourse> getMainCourses(){
		ObservableList<MainCourse> mainCoursess = FXCollections.observableArrayList();
		int i;
		for(i=0 ; i < mainCourses.size() ; i++) {
		mainCoursess.add(mainCourses.get(i));
		}
		return mainCoursess;
		
	}
	private ObservableList<Desert> getDeserts(){
		ObservableList<Desert> desertss = FXCollections.observableArrayList();
		int i;
		for(i=0 ; i < deserts.size() ; i++) {
		desertss.add(deserts.get(i));
		}
		return desertss;
		
	}


	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}


	public Scene getClientScene() {
		return ClientScene;
	}

	public void setClientScene(Scene clientScene) {
		ClientScene = clientScene;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public LoginForm getLoginFormScene() {
		return loginFormScene;
	}

	public void setLoginFormScene(LoginForm loginFormScene) {
		this.loginFormScene = loginFormScene;
	}

	public void setClient(String name , String username , String password) {
		this.client.setName(name);
		this.client.setUsername(username);
		this.client.setPassword(password);
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
	
	
}
