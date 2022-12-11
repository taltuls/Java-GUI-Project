package View;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import Exceptions.SameSectionException;

import Exceptions.EmptyFieldException;
import Model.Bird;
import Model.Mammal;
import Model.Reptile;
import Model.Section;
import Model.Snack;
import Model.SnackBar;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class RemoveController {

	Zoo zoo = Zoo.getInstance();

// Visitor

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane MainPanel;

	@FXML
	private Button saveButton;

	@FXML
	private ChoiceBox<Visitor> visitors = new ChoiceBox<>();

// Employee

	@FXML
	private ChoiceBox<ZooEmployee> employees = new ChoiceBox<>();

// Bird   
	@FXML
	private ChoiceBox<Bird> birds = new ChoiceBox<>();

// Mammal
	@FXML
	private ChoiceBox<Mammal> mammals = new ChoiceBox<>();

// Reptile
	@FXML
	private ChoiceBox<Reptile> reptiles = new ChoiceBox<>();

// Section
	@FXML
	private ChoiceBox<Section> sections = new ChoiceBox<>();

	@FXML
	private ChoiceBox<Section> newSection = new ChoiceBox<>();

//SnackBar
	@FXML
	private ChoiceBox<SnackBar> snackBars = new ChoiceBox<>();

// Snack
	@FXML
	private ChoiceBox<Snack> snacks = new ChoiceBox<>();

	ObservableList<Section> allSections = FXCollections.observableArrayList(zoo.getSections().values());
	ObservableList<Visitor> allVisitors = FXCollections.observableArrayList(zoo.getVisitors().values());
	ObservableList<ZooEmployee> allEmployees = FXCollections
			.observableArrayList(Zoo.getInstance().getEmployees().values());
	ObservableList<Mammal> allMammals = FXCollections.observableArrayList(zoo.getMammals().values());
	ObservableList<Bird> allBirds = FXCollections.observableArrayList(zoo.getBirds().values());
	ObservableList<Reptile> allReptiles = FXCollections.observableArrayList(zoo.getReptiles().values());
	ObservableList<SnackBar> allSnackBars = FXCollections.observableArrayList(zoo.getBars().values());
	ObservableList<Snack> allSnacks = FXCollections.observableArrayList(zoo.getSnacks().values());

	// initialize
	@FXML
	void initialize() {

		sections.setItems(allSections);
		visitors.setItems(allVisitors);
		employees.setItems(allEmployees);
		mammals.setItems(allMammals);
		reptiles.setItems(allReptiles);
		birds.setItems(allBirds);
		snackBars.setItems(allSnackBars);
		snacks.setItems(allSnacks);
		newSection.setItems(allSections);

	}

	// pop-up message
	private void showAlert(AlertType type, String title, String header, String text) {
		Alert alert = new Alert(type);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.setResizable(true);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		if (type == AlertType.ERROR) {
			File temp = new File("");
			String abPath = temp.getAbsolutePath();
			String path = new File(abPath + "/src/Media/fail.mp3").getAbsolutePath();
			MediaPlayer sound = new MediaPlayer(new Media(new File(path).toURI().toString()));
			sound.play();
		} else {
			File temp = new File("");
			String abPath = temp.getAbsolutePath();
			String path = new File(abPath + "/src/Media/success.mp3").getAbsolutePath();
			MediaPlayer sound = new MediaPlayer(new Media(new File(path).toURI().toString()));
			sound.play();
		}
		alert.showAndWait();
	}

	// save button
	@FXML
	void save(ActionEvent event) {
		boolean res = false;
		try {
			if (Main.choice.equals("removeSection")) {
				if (sections.getValue() == null || newSection == null) {
					throw new EmptyFieldException();

				} else if (sections.getValue().equals(newSection.getValue())) {
					throw new SameSectionException();
				}
				res = zoo.removeSection(sections.getValue(), newSection.getValue());
			}
			if (Main.choice.equals("removeEmployee")) {
				if (employees.getValue() == null) {
					throw new EmptyFieldException();
				}
				res = zoo.removeEmployee(employees.getValue());
			}
			if (Main.choice.equals("removeVisitor")) {
				if (visitors.getValue() == null) {
					throw new EmptyFieldException();
				}
				Visitor v = visitors.getValue();
				res = zoo.removeVisitor(v);

			}
			if (Main.choice.equals("removeMammal")) {
				if (mammals.getValue() == null) {
					throw new EmptyFieldException();
				}
				res = zoo.removeMammal(mammals.getValue());
			}
			if (Main.choice.equals("removeBird")) {
				if (birds.getValue() == null) {
					throw new EmptyFieldException();
				}
				res = zoo.removeBird(birds.getValue());
			}

			if (Main.choice.equals("removeReptile")) {
				if (reptiles.getValue() == null) {
					throw new EmptyFieldException();
				}
				res = zoo.removeReptile(reptiles.getValue());
			}

			if (Main.choice.equals("removeSnackBar")) {
				if (snackBars.getValue() == null) {
					throw new EmptyFieldException();
				}
				res = zoo.removeSnackBar(snackBars.getValue());
			}

			if (Main.choice.equals("removeSnack")) {
				if (snacks.getValue() == null) {
					throw new EmptyFieldException();
				}
				res = zoo.removeSnack(snacks.getValue());
			}

			if (res)
				showAlert(AlertType.INFORMATION, "Success", "Removed Successfully!", null);
			if (!res)
				showAlert(AlertType.ERROR, "Error", "Error!", "Try again please");

		} catch (EmptyFieldException e) {
			showAlert(AlertType.ERROR, "Error", "Error!", e.getMessage());
		} catch (SameSectionException s) {
			showAlert(AlertType.ERROR, "Error", "Error!", s.getMessage());
		}

	}

}
