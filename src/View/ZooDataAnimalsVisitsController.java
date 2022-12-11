package View;

import java.time.LocalDate;

import Model.Animal;
import Model.Visitor;
import Model.Zoo;
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

public class ZooDataAnimalsVisitsController {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane dataDisplayPane;

	@FXML
	private ChoiceBox<Visitor> visitors = new ChoiceBox<Visitor>();

	@FXML
	private Button choseVisButton;

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
	private Button chooseAnimals;

	ObservableList<Animal> tableList = FXCollections.observableArrayList();
	ObservableList<Visitor> visitorList = FXCollections.observableArrayList(zoo.getVisitors().values());

	@FXML
	public void initialize() {
		visitors.setItems(visitorList);
	}

	// shows all the visits of the chosen visitor
	@FXML
	void choseVisitor(ActionEvent event) {
		tableList.clear();
		try {

			animalsTable.setVisible(true);
			tableList.addAll(zoo.getAnimalVisitsByPeople().get(visitors.getValue()));
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
