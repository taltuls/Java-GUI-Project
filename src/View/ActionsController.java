package View;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import Exceptions.EmptyFieldException;
import Exceptions.MaximumCapcityException;
import Exceptions.SameSectionException;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import Model.Section;
import Model.Snack;
import Model.SnackBar;
import Model.Visitor;
import Model.Zoo;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ActionsController {

	Zoo zoo = Zoo.getInstance();
	
	@FXML
	private ChoiceBox<Visitor> visitorsOfEmpBox = new ChoiceBox<Visitor>();
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

	@FXML
	private ChoiceBox<Snack> snacks = new ChoiceBox<>();

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
	private Button Display;

	@FXML
	private ChoiceBox<Visitor> visitorsBox = new ChoiceBox<>();

	@FXML
	private Label chooseVisitorLabel;

	@FXML
	private ChoiceBox<Section> sectionsBox = new ChoiceBox<>();

	@FXML
	private Label chooseSectionLabel;
	
	@FXML
	private Button choseVisButton;
	
	// ********************************* lists to be shown ********************************* //

		ObservableList<Visitor> allVisitors = FXCollections.observableArrayList(zoo.getVisitors().values());

		ObservableList<Snack> snacksOfSection = FXCollections.observableArrayList();
		ObservableList<Snack> tableList = FXCollections.observableArrayList();
		ObservableList<Section> allSections = FXCollections.observableArrayList(zoo.getSections().values());

		ObservableList makeMyList() {
			ObservableList<Visitor> allEmpVisitors = null;
			if (Main.whoAmI != "admin") {
				allEmpVisitors = FXCollections
						.observableArrayList(zoo.getEmployees().get(Main.empId).getSection().getVisitors());
			}

			return allEmpVisitors;
		}

	// ********************************* move visitor to another section from employees' menu********************************* //
	@FXML
	void saveMoveVisitorEmployee(ActionEvent event) throws MaximumCapcityException { 
		try {
			Visitor v = visitorsOfEmpBox.getValue();
			Section newSection = sectionsBox.getValue();
			if (v == null || newSection == null) {
				throw new EmptyFieldException();

			} else if (newSection.equals(v.getSection())) {
				throw new SameSectionException();
			} else if (newSection == null
					|| (v.getSection().getMaxCapacity() / 2) <= v.getSection().getVisitors().size() - 1) {
				throw new MaximumCapcityException();
			}

			v.moveVisitorToSection(newSection);
			showAlert(AlertType.INFORMATION, "Success", "Moved Successfully!", null);


		} catch (EmptyFieldException e) {
			showAlert(AlertType.ERROR, "Error", "Error!", e.getMessage());
		} catch (SameSectionException s) {
			showAlert(AlertType.ERROR, "Error", "Error!", s.getMessage());
		} catch (MaximumCapcityException e) {
			showAlert(AlertType.ERROR, "Error", "Error!", e.getMessage());

		}

	}

	// ********************************* move visitor to another section from admin's menu********************************* //
	@FXML
	void saveMoveVisitor(ActionEvent event) throws MaximumCapcityException {

		try {
			Visitor v = visitorsBox.getValue();
			Section newSection = sectionsBox.getValue();
			if (v == null || newSection == null) {
				throw new EmptyFieldException();

			} else if (newSection.equals(v.getSection())) {
				throw new SameSectionException();
			} else if (newSection == null
					|| (v.getSection().getMaxCapacity() / 2) <= v.getSection().getVisitors().size() - 1) {
				throw new MaximumCapcityException();
			}

			v.moveVisitorToSection(newSection);
			showAlert(AlertType.INFORMATION, "Success", "Moved Successfully!", null);

		} catch (EmptyFieldException e) {
			showAlert(AlertType.ERROR, "Error", "Error!", e.getMessage());
		} catch (SameSectionException s) {
			showAlert(AlertType.ERROR, "Error", "Error!", s.getMessage());
		} catch (MaximumCapcityException e) {
			showAlert(AlertType.ERROR, "Error", "Error!", e.getMessage());

		}
	}
	
	
	// ********************************* initialize method ********************************* //
	@FXML
	void initialize() {

		visitors.setItems(allVisitors);
		if (Main.whoAmI != "admin") {
			visitorsOfEmpBox.setItems(makeMyList());
			visitors.setItems(makeMyList());
		}

		visitorsBox.setItems(allVisitors);
		sectionsBox.setItems(allSections);
	}


	// ********************************* pop-up message ********************************* //
	private void showAlert(AlertType type, String title, String header, String text) {
		Alert alert = new Alert(type);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.setResizable(true);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		if(type == AlertType.ERROR) {
	 		File temp = new File("");
			String abPath = temp.getAbsolutePath();
			String path = new File(abPath+"/src/Media/fail.mp3").getAbsolutePath();
	 		MediaPlayer sound = new MediaPlayer(new Media(new File(path).toURI().toString()));
	 		sound.play();
 		}
 		else {
	 		File temp = new File("");
			String abPath = temp.getAbsolutePath();
			String path = new File(abPath+"/src/Media/success.mp3").getAbsolutePath();
	 		MediaPlayer sound = new MediaPlayer(new Media(new File(path).toURI().toString()));
	 		sound.play();
 		}
		alert.showAndWait();
	}

	// ********************************* tableView of all snacks which are in the same section of the visitor ********************************* //
	@FXML
	void choseVisitor(ActionEvent event) {
		try {
			tableList.clear();
			SnackBar sb = visitors.getValue().getSection().getBar();
			tableList.addAll(sb.getSnacks());
			typeCol.setCellValueFactory(
					cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getClass().getSimpleName()));
			idCol.setCellValueFactory(new PropertyValueFactory<Snack, Integer>("Id"));
			nameCol.setCellValueFactory(new PropertyValueFactory<Snack, String>("snackName"));
			priceCol.setCellValueFactory(new PropertyValueFactory<Snack, Double>("price"));
			fattening.setCellValueFactory(new PropertyValueFactory<Snack, Boolean>("Fattening"));
			table.setItems(tableList);
		} catch(NullPointerException n) {
			table.setVisible(true);
		}

	}
	
	// ********************************* the visitor's selection of the snacks he would like to purchase ********************************* //
	@FXML
	void purchaseSnack(ActionEvent event) {

		ArrayList<Snack> selectSnacks = new ArrayList<Snack>();
		selectSnacks.addAll(table.getSelectionModel().getSelectedItems());

		for (Snack s : selectSnacks) {
			if (visitors.getValue().purchaseSnack(s)) {
				zoo.getSnacks().remove(s.getId());
				if (!zoo.getPurchasesByVisitors().containsKey(visitors.getValue())) {
					zoo.getPurchasesByVisitors().put(visitors.getValue(), new HashSet<Snack>());
				}

				zoo.getPurchasesByVisitors().get(visitors.getValue()).add(s);

			}
		}

		tableList.removeAll(selectSnacks);

		table.setItems(tableList);

	}
}
