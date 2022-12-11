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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ZooDataNotTreatedAnimal {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane dataDisplayPane;

	@FXML
	private ChoiceBox<ZooEmployee> employee = new ChoiceBox<ZooEmployee>();

	@FXML
	private TableView<Animal> animalsTable;

	@FXML
	private TableColumn<Animal, String> typeCol;

	@FXML
	private TableColumn<Animal, Integer> idCol;

	@FXML
	private TableColumn<Animal, String> nameCol;

	@FXML
	private TableColumn<Animal, Gender> genderCol;

	@FXML
	private TableColumn<Animal, LocalDate> birthDayCol;

	@FXML
	private TableColumn<Animal, AnimalFood> animalFoodCol;

	@FXML
	private TextField text;

	@FXML
	private Button choseEmpButton;

	ObservableList<Animal> tableList = FXCollections.observableArrayList();
	ObservableList<ZooEmployee> employeeList = FXCollections.observableArrayList(zoo.getEmployees().values());

	// initialize list
	@FXML
	public void initialize() {
		employee.setItems(employeeList);
	}

	// shows all the animals that the employee has taken care of
	@FXML
	void choseEmployee(ActionEvent event) {
		tableList.clear();
		try {
			if (!zoo.allAnimalsByWorker(employee.getValue()).isEmpty()) {
				animalsTable.setVisible(true);
				text.setVisible(false);
				tableList.addAll(zoo.allAnimalsByWorker(employee.getValue()));
				typeCol.setCellValueFactory(
						cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getClass().getSimpleName()));
				idCol.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("Id"));
				nameCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("Name"));
				genderCol.setCellValueFactory(new PropertyValueFactory<Animal, Gender>("Gender"));
				birthDayCol.setCellValueFactory(new PropertyValueFactory<Animal, LocalDate>("BirthDay"));
				animalFoodCol.setCellValueFactory(new PropertyValueFactory<Animal, AnimalFood>("Food"));
				animalsTable.setItems(tableList);

			} else if (zoo.allAnimalsByWorker(employee.getValue()) != null) {
				animalsTable.setVisible(true);
				text.setVisible(false);
				tableList.addAll(zoo.allAnimalsByWorker(employee.getValue()));
				typeCol.setCellValueFactory(
						cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getClass().getSimpleName()));
				idCol.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("Id"));
				nameCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("Name"));
				genderCol.setCellValueFactory(new PropertyValueFactory<Animal, Gender>("Gender"));
				birthDayCol.setCellValueFactory(new PropertyValueFactory<Animal, LocalDate>("BirthDay"));
				animalFoodCol.setCellValueFactory(new PropertyValueFactory<Animal, AnimalFood>("Food"));
				animalsTable.setItems(tableList);
			}

			else {
				animalsTable.setVisible(true);


			}
		} catch (NullPointerException n) {
			animalsTable.setVisible(true);
		}
	}

}
