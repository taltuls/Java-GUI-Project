package View;

import java.time.LocalDate;
import Model.Mammal;
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

public class ZooDataAnimalController {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane dataDisplayPane;

	@FXML
	private TableColumn<Mammal, Gender> genderCol;

	@FXML
	private TableView<Mammal> animalsTable;

	@FXML
	private TableColumn<Mammal, Integer> idCol;

	@FXML
	private TableColumn<Mammal, String> nameCol;

	@FXML
	private TableColumn<Mammal, LocalDate> birthDayCol;

	@FXML
	private TableColumn<Mammal, AnimalFood> animalFoodCol;

	ObservableList<Mammal> tableList = FXCollections.observableArrayList();

	// initialize lists and show
	@FXML
	public void initialize() {

		tableList.clear();

		tableList.addAll(zoo.getMammals().values());

		idCol.setCellValueFactory(new PropertyValueFactory<Mammal, Integer>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Mammal, String>("name"));
		genderCol.setCellValueFactory(new PropertyValueFactory<Mammal, Gender>("Gender"));
		birthDayCol.setCellValueFactory(new PropertyValueFactory<Mammal, LocalDate>("birthDay"));
		animalFoodCol.setCellValueFactory(new PropertyValueFactory<Mammal, AnimalFood>("food"));

		animalsTable.setItems(tableList);

	}

}
