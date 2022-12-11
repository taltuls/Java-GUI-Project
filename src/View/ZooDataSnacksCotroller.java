package View;

import Model.Snack;
import Model.SnackBar;
import Model.Zoo;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ZooDataSnacksCotroller {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane dataDisplayPane;

	@FXML
	private ChoiceBox<SnackBar> snackBar = new ChoiceBox<SnackBar>();

	@FXML
	private TableView<Snack> table;

	@FXML
	private Button Display;

	@FXML
	private TableColumn<Snack, String> typeCol;

	@FXML
	private TableColumn<Snack, String> nameCol;

	@FXML
	private TableColumn<Snack, Double> priceCol;

	@FXML
	private TableColumn<Snack, Integer> idCol;

	@FXML
	private TableColumn<Snack, Boolean> fattening;

	ObservableList<Snack> tableList = FXCollections.observableArrayList();

	// shows all the snacks in the zoo
	@FXML
	public void initialize() {
		tableList.clear();

		tableList.addAll(zoo.getSnacks().values());

		typeCol.setCellValueFactory(
				cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getClass().getSimpleName()));
		idCol.setCellValueFactory(new PropertyValueFactory<Snack, Integer>("Id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Snack, String>("snackName"));
		priceCol.setCellValueFactory(new PropertyValueFactory<Snack, Double>("price"));
		fattening.setCellValueFactory(new PropertyValueFactory<Snack, Boolean>("Fattening"));
		table.setItems(tableList);

	}
}
