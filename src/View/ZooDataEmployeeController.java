package View;

import java.time.LocalDate;

import Model.Section;
import Model.Zoo;
import Model.ZooEmployee;
import Utils.Gender;
import Utils.Job;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ZooDataEmployeeController {
	
	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane dataDisplayPane;

	@FXML
	private TableView<ZooEmployee> table;

	@FXML
	private TableColumn<ZooEmployee, String> firstNameCol;

	@FXML
	private TableColumn<ZooEmployee, String> lastName;

	@FXML
	private TableColumn<ZooEmployee, Integer> idcCol;

	@FXML
	private TableColumn<ZooEmployee, Job> jobCol;

	@FXML
	private TableColumn<ZooEmployee, Section> sectionCol;

	@FXML
	private TableColumn<ZooEmployee, Gender> genderCol;

	@FXML
	private TableColumn<ZooEmployee, LocalDate> birthDateCol;

	ObservableList<ZooEmployee> tableList = FXCollections.observableArrayList();

	// show all employees in the zoo
	@FXML
	public void initialize() {
		tableList.clear();
		tableList.addAll(zoo.getEmployees().values());
		idcCol.setCellValueFactory(new PropertyValueFactory<ZooEmployee, Integer>("Id"));
		firstNameCol.setCellValueFactory(new PropertyValueFactory<ZooEmployee, String>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<ZooEmployee, String>("lastName"));
		sectionCol.setCellValueFactory(new PropertyValueFactory<ZooEmployee, Section>("section"));
		genderCol.setCellValueFactory(new PropertyValueFactory<ZooEmployee, Gender>("gender"));
		birthDateCol.setCellValueFactory(new PropertyValueFactory<ZooEmployee, LocalDate>("birthDay"));
		jobCol.setCellValueFactory(new PropertyValueFactory<ZooEmployee, Job>("job"));
		table.setItems(tableList);

	}

}
