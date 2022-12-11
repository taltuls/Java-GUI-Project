package View;

import java.time.LocalDate;
import java.util.ArrayList;

import Exceptions.AnimalsVisitsException;
import Model.Animal;
import Model.Bird;
import Model.Mammal;
import Model.Section;
import Model.Visitor;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class AnimalVisitsController {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane MainPanel;

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

	// initialize list
	@FXML
	public void initialize() {
		visitors.setItems(visitorList);

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

	// show all animals that a visitor can visit
	@FXML
	void choseVisitor(ActionEvent event) {
		tableList.clear();
		try {
			Section sec = visitors.getValue().getSection();
			for (Animal a : zoo.getAllAnimalsBySectionMaxVisits(sec)) {
				if (a instanceof Mammal || a instanceof Bird) {
					if ((!zoo.getAnimalVisitsByPeople().containsKey(visitors.getValue()))
							|| !zoo.getAnimalVisitsByPeople().get(visitors.getValue()).contains(a)) {
						if (a.getVisitCounter() < 5) {

							tableList.add(a);
						}
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
		} catch (NullPointerException n) {
			animalsTable.setVisible(true);
		}
	}

	// choose the animal the visitor would like to visit
	@FXML
	void chooseAnimalsToVisit(ActionEvent event) {
		ArrayList<Animal> selectAnimals = new ArrayList<Animal>();
		selectAnimals.addAll(animalsTable.getSelectionModel().getSelectedItems());
		for (Animal a : selectAnimals) {
			if (a instanceof Mammal) {
				try {
					((Mammal) a).visitcount(visitors.getValue());
				} catch (AnimalsVisitsException e) {
					showAlert(AlertType.WARNING, "Animal Visits Exception",
							"Pay Attention, this mammal needs to get her treatment and needs to rest!", "");
				}

			}
			if (a instanceof Bird) {
				try {
					((Bird) a).visitcount(visitors.getValue());
				} catch (AnimalsVisitsException e) {
					showAlert(AlertType.WARNING, "Animal Visits Exception",
							"Pay Attention, this mammal needs to get her treatment and needs to rest!", "");
				}
			}
		}
		tableList.removeAll(selectAnimals);

		animalsTable.setItems(tableList);

	}

}
