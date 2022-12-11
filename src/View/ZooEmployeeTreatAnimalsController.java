package View;

import java.time.LocalDate;
import java.util.ArrayList;

import Exceptions.AnimalsVisitsException;
import Model.Animal;
import Model.Bird;
import Model.Mammal;
import Model.Section;
import Model.Zoo;
import Utils.AnimalFood;
import Utils.Gender;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class ZooEmployeeTreatAnimalsController {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private Button chooseAnimals;

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

	ObservableList<Animal> tableList = FXCollections.observableArrayList();

	// shows all the animals that the chosen employee can take care of
	@FXML
	public void initialize() {
		tableList.clear();
		Section sec = zoo.getEmployees().get(Main.empId).getSection();
		for (Animal a : zoo.getAllAnimalsBySectionMaxVisits(sec)) {
			if (a instanceof Mammal || a instanceof Bird) {

				if (a.getVisitCounter() >= 5) {
					tableList.addAll(a);
				}

			}
		}
		typeCol.setCellValueFactory(
				cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getClass().getSimpleName()));
		idCol.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("Id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("Name"));
		genderCol.setCellValueFactory(new PropertyValueFactory<Animal, Gender>("Gender"));
		birthDayCol.setCellValueFactory(new PropertyValueFactory<Animal, LocalDate>("BirthDay"));
		animalFoodCol.setCellValueFactory(new PropertyValueFactory<Animal, AnimalFood>("Food"));
		animalsTable.setItems(tableList);

	}

	// shows a tableView with the animals the employee took care of
	@FXML
	void TreatAnimal(ActionEvent event) {
		ArrayList<Animal> selectAnimals = new ArrayList<Animal>();
		selectAnimals.addAll(animalsTable.getSelectionModel().getSelectedItems());
		for (Animal a : selectAnimals) {
			if (a instanceof Mammal) {
				try {
					((Mammal) a).visitcount(zoo.getEmployees().get(Main.empId));
				} catch (AnimalsVisitsException e) {
					showAlert(AlertType.INFORMATION, "Animal Visits Exception",
							"Pay Attention! This mammal needs " + "to get her treatment and needs to rest", "");
				}

			}
			if (a instanceof Bird) {
				try {
					((Bird) a).visitcount(zoo.getEmployees().get(Main.empId));
				} catch (AnimalsVisitsException e) {
					showAlert(AlertType.INFORMATION, "Animal Visits Exception",
							"Pay Attention! This bird needs " + "to get her treatment and needs to rest", "");
				}
			}
		}
		tableList.removeAll(selectAnimals);

		animalsTable.setItems(tableList);

	}

	// pop-up message
	private void showAlert(AlertType type, String title, String header, String text) {
		Alert alert = new Alert(type);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.setResizable(true);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		alert.showAndWait();
	}

}
