package View;

import java.time.LocalDate;

import Model.Animal;
import Model.Section;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class AllAnimalBySectionController {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane MainPanel;

	@FXML
	private Label title;

	@FXML
	private Button Display;

	@FXML
	private ChoiceBox<Section> sections = new ChoiceBox<Section>();

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
	ObservableList<Section> sectionsList = FXCollections.observableArrayList(zoo.getSections().values());

	// initialize list
	@FXML
	public void initialize() {
		sections.setItems(sectionsList);

	}

	// when the button is pressed
	@FXML
	void submit(ActionEvent event) {
		try {
			tableList.clear();
			Section sec = sections.getValue();
			tableList.addAll(zoo.getAllAnimalsBySectionMaxVisits(sec));
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
