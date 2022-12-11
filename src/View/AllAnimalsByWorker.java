package View;

import java.time.LocalDate;

import Model.Animal;
import Model.Zoo;
import Model.ZooEmployee;
import Utils.AnimalFood;
import Utils.Gender;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class AllAnimalsByWorker {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane MainPanel;

	@FXML
	private ChoiceBox<ZooEmployee> worker = new ChoiceBox<>();

	@FXML
	private Button Display;

	@FXML
	private TableColumn<Animal, Gender> genderCol;

	@FXML
	private TableView<Animal> animalsTable;

	@FXML
	private TableColumn<Animal, String> typeCol;

	@FXML
	private TableColumn<Animal, Integer> idCol;

	@FXML
	private TableColumn<Animal, String> nameCol;

	@FXML
	private TableColumn<Animal, LocalDate> birthDayCol;

	@FXML
	private TableColumn<Animal, AnimalFood> animalFoodCol;

	ObservableList<Animal> tableList = FXCollections.observableArrayList();
	ObservableList<ZooEmployee> employeesList = FXCollections.observableArrayList(zoo.getEmployees().values());

	// initialize
	@FXML
	public void initialize() {
		worker.setItems(employeesList);
	}

	// show tableView when the button is pressed
	@FXML
	void submit(ActionEvent event) {
		try {
			tableList.clear();
			ZooEmployee emp = worker.getValue();
			animalsTable.setVisible(true);
			tableList.addAll(zoo.allAnimalsByWorker(emp));
			typeCol.setCellValueFactory(
					cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getClass().getSimpleName()));
			idCol.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("Id"));
			nameCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("Name"));
			genderCol.setCellValueFactory(new PropertyValueFactory<Animal, Gender>("Gender"));
			birthDayCol.setCellValueFactory(new PropertyValueFactory<Animal, LocalDate>("BirthDay"));
			animalFoodCol.setCellValueFactory(new PropertyValueFactory<Animal, AnimalFood>("Food"));
			animalsTable.setItems(tableList);
		} catch (NullPointerException n) {
			animalsTable.setVisible(true);
		}

	}

}
