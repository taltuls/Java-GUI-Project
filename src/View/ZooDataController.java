package View;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

public class ZooDataController {

	@FXML
	private RadioButton radioButton;

	@FXML
	private ToggleGroup data;

	@FXML
	private AnchorPane MainPanel;

	@FXML
	private Label label;

	@FXML
	private AnchorPane dataDisplayPane;

	// **************** zoo data  **************** //
	@FXML
	void reptileData(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooDataReptile.fxml"));
		LoadScreen(loader);

	}

	@FXML
	void BirdsData(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooDataBirsd.fxml"));
		LoadScreen(loader);

	}

	@FXML
	void animalsData(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooDataAnimals.fxml"));
		LoadScreen(loader);

	}

	@FXML
	void sectionsData(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooDataSection.fxml"));
		LoadScreen(loader);

	}

	@FXML
	void snackBarData(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooDataSnackBar.fxml"));
		LoadScreen(loader);

	}

	@FXML
	void snacksData(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooDataSnacks.fxml"));
		LoadScreen(loader);

	}

	@FXML
	void visitorData(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooDataVisitor.fxml"));
		LoadScreen(loader);

	}

	@FXML
	void displayEmployeeData(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooDataEmployee.fxml"));
		LoadScreen(loader);

	}

	@FXML
	void notTreatmentAnimals(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooDataNotTreatedAnimal.fxml"));
		LoadScreen(loader);

	}

	@FXML
	void AnimalsVisitedByVisitor(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooDataVisitAnimal.fxml"));
		LoadScreen(loader);

	}

	@FXML
	void treatAnimals(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooDataTreatedAnimal.fxml"));
		LoadScreen(loader);

	}

	void LoadScreen(FXMLLoader loader) {
		try {

			AnchorPane pane = loader.load();
			dataDisplayPane.getChildren().clear();
			dataDisplayPane.getChildren().add(pane);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
