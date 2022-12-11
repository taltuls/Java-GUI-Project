package View;

import Model.Snack;
import Model.SnackBar;
import Model.Zoo;
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

public class AllSnackBySnackBarController {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane MainPanel;

	@FXML
	private Label title;

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

	ObservableList<SnackBar> snackBarList = FXCollections.observableArrayList(zoo.getBars().values());
	ObservableList<Snack> tableList = FXCollections.observableArrayList();

	// initialize list
	@FXML
	public void initialize() {
		snackBar.setItems(snackBarList);

	}

	// show table View when the button is clicked
	@FXML
	void submit(ActionEvent event) {
		try {
			tableList.clear();
			SnackBar bar = snackBar.getValue();
			for (Snack s : bar.getSnacks()) {
				tableList.addAll(s);
			}

			typeCol.setCellValueFactory(
					cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getClass().getSimpleName()));
			idCol.setCellValueFactory(new PropertyValueFactory<Snack, Integer>("Id"));
			nameCol.setCellValueFactory(new PropertyValueFactory<Snack, String>("snackName"));
			priceCol.setCellValueFactory(new PropertyValueFactory<Snack, Double>("price"));
			fattening.setCellValueFactory(new PropertyValueFactory<Snack, Boolean>("Fattening"));
			table.setItems(tableList);
		} catch (NullPointerException n) {
			table.setVisible(true);
		}

	}

}
