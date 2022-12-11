package View;

import Model.Section;
import Model.SnackBar;
import Model.Zoo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ZooDataSectionController {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane dataDisplayPane;

	@FXML
	private TableView<Section> animalsTable;

	@FXML
	private TableColumn<Section, Integer> idCol;

	@FXML
	private TableColumn<Section, String> nameCol;

	@FXML
	private TableColumn<Section, Integer> maxCapcityCol;

	@FXML
	private TableColumn<Section, SnackBar> barCol;

	@FXML
	private TableColumn<Section, Double> revenueCol;

	ObservableList<Section> tableList = FXCollections.observableArrayList();

	// shows all the sections
	@FXML
	public void initialize() {
		tableList.clear();
		tableList.addAll(zoo.getSections().values());
		idCol.setCellValueFactory(new PropertyValueFactory<Section, Integer>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Section, String>("sectionName"));
		maxCapcityCol.setCellValueFactory(new PropertyValueFactory<Section, Integer>("maxCapacity"));
		revenueCol.setCellValueFactory(new PropertyValueFactory<Section, Double>("todayRevenue"));
		barCol.setCellValueFactory(new PropertyValueFactory<Section, SnackBar>("bar"));
		animalsTable.setItems(tableList);

	}

}
