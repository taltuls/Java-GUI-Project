package View;

import java.time.LocalDate;

import Model.Bird;
import Model.Zoo;
import Utils.AnimalFood;
import Utils.Gender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ZooDataBirdsController {

	Zoo zoo = Zoo.getInstance();
	
	@FXML
	private AnchorPane dataDisplayPane;

	@FXML
	private TableView<Bird> animalsTable;

	@FXML
	private TableColumn<Bird, Integer> idCol;

	@FXML
	private TableColumn<Bird, String> nameCol;

	@FXML
	private TableColumn<Bird, Gender> genderCol;

	@FXML
	private TableColumn<Bird, LocalDate> birthDayCol;

	@FXML
	private TableColumn<Bird, AnimalFood> animalFoodCol;
	
	@FXML
	private TableColumn<Bird, Boolean> canFlyCol;

	@FXML
	private TableColumn<Bird, Boolean> canTakePIcCol;

	ObservableList<Bird> tableList = FXCollections.observableArrayList();

	// shows all birds in the zoo
	@FXML
	public void initialize() {
		tableList.clear();

		tableList.addAll(zoo.getBirds().values());

		idCol.setCellValueFactory(new PropertyValueFactory<Bird, Integer>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Bird, String>("name"));
		genderCol.setCellValueFactory(new PropertyValueFactory<Bird, Gender>("gender"));
		birthDayCol.setCellValueFactory(new PropertyValueFactory<Bird, LocalDate>("birthDay"));
		animalFoodCol.setCellValueFactory(new PropertyValueFactory<Bird, AnimalFood>("food"));
		canFlyCol.setCellValueFactory(new PropertyValueFactory<Bird, Boolean>("canFly"));
		canTakePIcCol.setCellValueFactory(new PropertyValueFactory<Bird, Boolean>("canTakePic"));

		animalsTable.setItems(tableList);

	}

}
