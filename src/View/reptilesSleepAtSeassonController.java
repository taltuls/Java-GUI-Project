package View;

import java.time.LocalDate;

import Model.Reptile;
import Model.Section;
import Model.Zoo;
import Utils.AnimalFood;
import Utils.Gender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class reptilesSleepAtSeassonController {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane MainPanel;

	@FXML
	private Label title;

	@FXML
	private Button Display;

	@FXML
	private TableView<Reptile> table;

	@FXML
	private TableColumn<Reptile, String> nameCol;

	@FXML
	private TableColumn<Reptile, Integer> idCol;

	@FXML
	private TableColumn<Reptile, AnimalFood> foodCol;

	@FXML
	private TableColumn<Reptile, LocalDate> birthDayCol;

	@FXML
	private TableColumn<Reptile, Gender> genderCol;

	@FXML
	private TableColumn<Reptile, Section> sectionCol;

	ObservableList<Reptile> tableList = FXCollections.observableArrayList();

	// show reptiles sleep at seasson query
	@FXML
	void submit(ActionEvent event) {
		try {
			tableList.clear();
			tableList.addAll(zoo.reptilesSleepAtSeasson());
			nameCol.setCellValueFactory(new PropertyValueFactory<Reptile, String>("name"));
			idCol.setCellValueFactory(new PropertyValueFactory<Reptile, Integer>("id"));
			genderCol.setCellValueFactory(new PropertyValueFactory<Reptile, Gender>("Gender"));
			birthDayCol.setCellValueFactory(new PropertyValueFactory<Reptile, LocalDate>("BirthDay"));
			foodCol.setCellValueFactory(new PropertyValueFactory<Reptile, AnimalFood>("Food"));
			sectionCol.setCellValueFactory(new PropertyValueFactory<Reptile, Section>("section"));
			table.setItems(tableList);
			
		} catch(NullPointerException n) {
			table.setVisible(true);
		}

	}

}
