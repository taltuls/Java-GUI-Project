package View;

import java.time.LocalDate;

import Model.Section;
import Model.Visitor;
import Model.Zoo;
import Utils.Gender;
import Utils.TicketType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ZooDataVisitorController {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane dataDisplayPane;

	@FXML
	private TableView<Visitor> table;

	@FXML
	private TableColumn<Visitor, String> firstNameCol;

	@FXML
	private TableColumn<Visitor, String> lastName;

	@FXML
	private TableColumn<Visitor, Integer> idcCol;

	@FXML
	private TableColumn<Visitor, TicketType> ticketTypeCol;

	@FXML
	private TableColumn<Visitor, Section> sectionCol;

	@FXML
	private TableColumn<Visitor, Gender> genderCol;

	@FXML
	private TableColumn<Visitor, LocalDate> birthDateCol;

	ObservableList<Visitor> tableList = FXCollections.observableArrayList();

	// shows all the visitors that are currently in the zoo
	@FXML
	public void initialize() {
		tableList.clear();
		tableList.addAll(zoo.getVisitors().values());
		idcCol.setCellValueFactory(new PropertyValueFactory<Visitor, Integer>("id"));
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Visitor, String>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<Visitor, String>("lastName"));
		ticketTypeCol.setCellValueFactory(new PropertyValueFactory<Visitor, TicketType>("ticket"));
		sectionCol.setCellValueFactory(new PropertyValueFactory<Visitor, Section>("section"));
		genderCol.setCellValueFactory(new PropertyValueFactory<Visitor, Gender>("gender"));
		birthDateCol.setCellValueFactory(new PropertyValueFactory<Visitor, LocalDate>("birthDay"));
		table.setItems(tableList);

	}
}
