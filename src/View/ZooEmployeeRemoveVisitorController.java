package View;

import Exceptions.EmptyFieldException;
import Model.Visitor;
import Model.Zoo;
import Model.ZooEmployee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class ZooEmployeeRemoveVisitorController {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane MainPanel;

	@FXML
	private ChoiceBox<ZooEmployee> employees = new ChoiceBox<ZooEmployee>();

	@FXML
	private ChoiceBox<Visitor> visitors = new ChoiceBox<Visitor>();

	@FXML
	private Button saveButton;

	ObservableList<Visitor> allVisitors = FXCollections
			.observableArrayList(zoo.getEmployees().get(Main.empId).getSection().getVisitors());
	ObservableList<ZooEmployee> allemp = FXCollections
			.observableArrayList(zoo.getEmployees().get(Main.empId).getSection().getEmployees());

	@FXML
	void initialize() {
		visitors.setItems(allVisitors);
		allemp.removeAll(new ZooEmployee(Main.empId));
		employees.setItems(allemp);
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

	// save button
	@FXML
	void save(ActionEvent event) {

		boolean res = false;
		try {
			if (Main.choice.equals("removeVisitor")) {
				if (visitors.getValue() == null) {
					throw new EmptyFieldException();
				}
				Visitor v = visitors.getValue();
				res = zoo.removeVisitor(v);

			}
			if (Main.choice.equals("removeEmployee")) {
				if (employees.getValue() == null) {
					throw new EmptyFieldException();
				}
				res = zoo.removeEmployee(employees.getValue());
			}
			if (res)
				showAlert(AlertType.INFORMATION, "Success", "Removed Successfully!", null);
			if (!res)
				showAlert(AlertType.ERROR, "Error", "Error!", "Try again please");
		} catch (EmptyFieldException e) {
			showAlert(AlertType.ERROR, "Error", "Error!", e.getMessage());
		}
	}

}
