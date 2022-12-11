package View;

import java.net.URL;

import java.util.ArrayList;

import java.util.ResourceBundle;

import Exceptions.EmptyFieldException;

import Model.Animal;
import Model.Reptile;
import Model.Section;

import Model.Zoo;
import Model.ZooEmployee;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class QueriesController {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private TextArea textDisplay;

	private final String nullPointer = "The system cannot complete your request. Some information might be missing.";

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane MainPanel;

	@FXML
	private Label title;

	@FXML
	private Label title2;

	@FXML
	private ComboBox<ZooEmployee> workers = new ComboBox<ZooEmployee>();

	@FXML
	private ChoiceBox<Section> sections = new ChoiceBox<Section>();

	@FXML
	private Button Display;

	@FXML
	private TextField visitorHeadLine;

	@FXML
	private TextField savingHeadLine;

	ObservableList<Section> sectionsList = FXCollections.observableArrayList(zoo.getSections().values());
	ObservableList<ZooEmployee> employeesList = FXCollections.observableArrayList(zoo.getEmployees().values());
	ObservableList<Section> textList = FXCollections.observableArrayList();


	private void printAll(String title, String header, String toPrint) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);

		TextArea textArea = new TextArea(toPrint);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);
		alert.getDialogPane().setExpanded(true);

		alert.showAndWait();
	}

	// initialize lists
	@FXML
	void initialize() {

		workers.setItems(employeesList);
		sections.setItems(sectionsList);
		textDisplay.setVisible(false);
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

	// show queries
	@FXML
	void submit(ActionEvent event) {
		textDisplay.setVisible(true);
		textList.clear();
		textList.addAll(zoo.getMaxVisitorsVSMaxWorkers());
		textDisplay.setText(textList.toString());

		try {

			if (Main.choice.equals("findAllAnimalsByWorker")) {

				if (workers.getValue() == null)
					throw new EmptyFieldException();
				String title = "All Animals By " + workers.getValue();
				String header = "Worker " + workers.getValue() + " Animals";
				String toPrint = zoo.getAnimalTreatedByZooEmployee().values().toString();
				printAll(title, header, toPrint);
			}
			if (Main.choice.equals("getAllAnimalsBySection")) {

				ArrayList<Animal> animals = zoo.getAllAnimalsBySectionMaxVisits(sections.getValue());
				String toPrint = "";
				if (animals == null)
					toPrint = "No animals to show";
				else {
					for (Animal a : animals)
						toPrint += (a.toString() + "\n");
				}
				String title = "All Animals By " + sections.getValue().getSectionName();
				String header = "Section " + sections.getValue().getSectionName() + " Animals";
				printAll(title, header, toPrint);

			}
			if (Main.choice.equals("getAllDiscounts")) {

				String title = "All Savings of Visitors";
				String header = "Savings of Visitors";
				String toPrint = zoo.geAllDiscountAmount().toString();
				printAll(title, header, toPrint);
			}

			if (Main.choice.equals("reptilesSleep")) {

				ArrayList<Reptile> reptiles = zoo.reptilesSleepAtSeasson();
				String toPrint = "";
				if (reptiles.isEmpty())
					toPrint = "No reptiles to show";
				else {
					for (Reptile r : reptiles)
						toPrint += (r.toString() + "\n");
				}
				String title = "Reptiles Sleep At Season";
				String header = "Showing reptiles";
				printAll(title, header, toPrint);
			}

		} catch (EmptyFieldException e) {
			showAlert(AlertType.ERROR, "Error", "Cannot Display Information", e.getMessage());
			return;
		} catch (NullPointerException e) {
			showAlert(AlertType.ERROR, "Error", "System Error", nullPointer);
			return;
		}
	}

}
