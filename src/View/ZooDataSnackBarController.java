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

public class ZooDataSnackBarController {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane dataDisplayPane;

	@FXML
	private TableView<SnackBar> table;

	@FXML
	private TableColumn<SnackBar, Integer> idCol;

	@FXML
	private TableColumn<SnackBar, String> nameCol;

	@FXML
	private TableColumn<SnackBar, Section> sectionCol;

	@FXML
	private TableColumn<SnackBar, Double> profitCol;

	@FXML
	private TableColumn<SnackBar, Double> zooPercentageCol;

	@FXML
	private TableColumn<Section, Double> revenueCol;

	ObservableList<SnackBar> tableList = FXCollections.observableArrayList();

	// shows all the snackBars in the zoo
	@FXML
	public void initialize() {
		tableList.clear();
		tableList.addAll(zoo.getBars().values());
		idCol.setCellValueFactory(new PropertyValueFactory<SnackBar, Integer>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<SnackBar, String>("barName"));
		sectionCol.setCellValueFactory(new PropertyValueFactory<SnackBar, Section>("section"));
		profitCol.setCellValueFactory(new PropertyValueFactory<SnackBar, Double>("profit"));
		zooPercentageCol.setCellValueFactory(new PropertyValueFactory<SnackBar, Double>("zooPercentage"));
		table.setItems(tableList);
	}

}
