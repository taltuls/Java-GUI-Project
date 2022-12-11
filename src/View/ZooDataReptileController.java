package View;

import java.time.LocalDate;

import Model.Reptile;
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

public class ZooDataReptileController {

	Zoo zoo = Zoo.getInstance();
	
	@FXML
	private AnchorPane dataDisplayPane;

	@FXML
	private TableView<Reptile> animalsTable;

	@FXML
	private TableColumn<Reptile, Integer> idCol;

	@FXML
	private TableColumn<Reptile, String> nameCol;

	@FXML
	private TableColumn<Reptile, Gender> genderCol;

	@FXML
	private TableColumn<Reptile, LocalDate> birthDayCol;

	@FXML
	private TableColumn<Reptile, Boolean> HibernationCol;

	@FXML
	private TableColumn<Reptile, AnimalFood> animalFoodCol;
	
	ObservableList<Reptile> tableList = FXCollections.observableArrayList();

	// shows all the reptiles
	@FXML
	public void initialize() {
		tableList.clear();

		tableList.addAll(zoo.getReptiles().values());

		idCol.setCellValueFactory(new PropertyValueFactory<Reptile, Integer>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Reptile, String>("name"));
		genderCol.setCellValueFactory(new PropertyValueFactory<Reptile, Gender>("gender"));
		birthDayCol.setCellValueFactory(new PropertyValueFactory<Reptile, LocalDate>("birthDay"));
		animalFoodCol.setCellValueFactory(new PropertyValueFactory<Reptile, AnimalFood>("food"));
		HibernationCol.setCellValueFactory(new PropertyValueFactory<Reptile, Boolean>("seasonSleep"));

		animalsTable.setItems(tableList);

	}

}
