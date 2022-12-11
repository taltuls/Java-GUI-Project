package View;

import Model.Snack;
import Model.Visitor;
import Model.Zoo;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PurchasesController {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private ChoiceBox<Visitor> visitors = new ChoiceBox<>();
	@FXML
	private TableView<Snack> table;

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

	@FXML
	private Button choseVisButton;

	ObservableList<Visitor> allVisitors = FXCollections.observableArrayList(zoo.getVisitors().values());
	ObservableList<Snack> tableList = FXCollections.observableArrayList();

	// shows all the snacks that the visitor has bought
	@FXML
	void choseVisitor(ActionEvent event) {
		tableList.clear();

		try {
			tableList.addAll(zoo.getPurchasesByVisitors().get(visitors.getValue()));
			table.setVisible(true);
			typeCol.setCellValueFactory(
					cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getClass().getSimpleName()));
			idCol.setCellValueFactory(new PropertyValueFactory<Snack, Integer>("Id"));
			nameCol.setCellValueFactory(new PropertyValueFactory<Snack, String>("snackName"));
			priceCol.setCellValueFactory(new PropertyValueFactory<Snack, Double>("price"));
			fattening.setCellValueFactory(new PropertyValueFactory<Snack, Boolean>("Fattening"));
			table.setItems(tableList);

		} catch (NullPointerException p) {
			table.setVisible(true);
		}

	}

	@FXML
	void initialize() {
		visitors.setItems(allVisitors);
	}

}
