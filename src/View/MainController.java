package View;

import java.io.IOException;

import java.time.LocalTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Region;

public class MainController {

	@FXML
	private AnchorPane anchorP;

	@FXML
	private Button homeButton;

	@FXML
	private AnchorPane MainPanel;

// *************** load screen of all options in menu *************** //
	@FXML
	void home(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Home.fxml"));
		Main.choice = "home";
		LoadScreen(loader);
	}

	@FXML
	private Label test;

	@FXML
	void addEmployee(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddEmployee.fxml"));
		Main.choice = "addEmployee";
		LoadScreen(loader);

	}

	@FXML
	void addVisitor(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddVisitor.fxml"));
		Main.choice = "addVisitor";
		LoadScreen(loader);

	}

	@FXML
	void addMammal(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddMammal.fxml"));
		Main.choice = "addMammal";
		LoadScreen(loader);

	}

	@FXML
	void addBird(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddBird.fxml"));
		Main.choice = "addBird";
		LoadScreen(loader);

	}

	@FXML
	void addReptile(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddReptile.fxml"));
		Main.choice = "addReptile";
		LoadScreen(loader);
	}

	@FXML
	void addDrink(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddDrink.fxml"));
		Main.choice = "addDrink";
		LoadScreen(loader);

	}

	@FXML
	void addFood(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddFood.fxml"));
		Main.choice = "addFood";
		LoadScreen(loader);

	}

	@FXML
	void addSection(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddSection.fxml"));
		Main.choice = "addSection";
		LoadScreen(loader);

	}

	@FXML
	void addSnackBar(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddSnackBar.fxml"));
		Main.choice = "addSnackBar";
		LoadScreen(loader);
	}

	@FXML
	void removeVisitor(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RemoveVisitor.fxml"));
		Main.choice = "removeVisitor";
		LoadScreen(loader);
	}

	@FXML
	void removeBird(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RemoveBird.fxml"));
		Main.choice = "removeBird";
		LoadScreen(loader);
	}

	@FXML
	void removeEmployee(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RemoveEmployee.fxml"));
		Main.choice = "removeEmployee";
		LoadScreen(loader);
	}

	@FXML
	void removeMammal(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RemoveMammal.fxml"));
		Main.choice = "removeMammal";
		LoadScreen(loader);
	}

	@FXML
	void removeReptile(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RemoveReptile.fxml"));
		Main.choice = "removeReptile";
		LoadScreen(loader);
	}

	@FXML
	void removeSection(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RemoveSection.fxml"));
		Main.choice = "removeSection";
		LoadScreen(loader);
	}

	@FXML
	void removeSnack(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RemoveSnack.fxml"));
		Main.choice = "removeSnack";
		LoadScreen(loader);
	}

	@FXML
	void removeSnackBar(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RemoveSnackBar.fxml"));
		Main.choice = "removeSnackBar";
		LoadScreen(loader);
	}

	@FXML
	void allAnimalsByWorker(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FindAllAnimalsByWorker.fxml"));
		Main.choice = "allAnimalsByWorker";
		LoadScreen(loader);
	}

	// animals
	@FXML
	void VisitAnimal(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AnimalsVisits.fxml"));
		LoadScreen(loader);

	}

	@FXML
	void treatAnimal(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AnimalsTreatment.fxml"));
		LoadScreen(loader);

	}

	@FXML
	void allSnacksBySnackBar(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AllSnacksBySnackBar.fxml"));
		LoadScreen(loader);
	}

	@FXML
	void getAllAnimalsBySection(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/GetAllAnimalsBySection.fxml"));
		Main.choice = "getAllAnimalsBySection";
		LoadScreen(loader);
	}

	@FXML
	void getAllDiscounts(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/GetAllDiscounts.fxml"));
		Main.choice = "getAllDiscounts";
		LoadScreen(loader);
	}

	@FXML
	void getMaxVsMin(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/GetMaxVsMin.fxml"));
		Main.choice = "getMaxVsMin";
		LoadScreen(loader);
	}

	@FXML
	void reptilesSleep(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ReptilesSleep.fxml"));
		Main.choice = "reptilesSleep";
		LoadScreen(loader);
	}

	@FXML
	void purchases(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Purchases.fxml"));
		Main.choice = "purchases";
		LoadScreen(loader);
	}

	@FXML
	void newPurchase(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewPurchase.fxml"));
		Main.choice = "newPurchase";
		LoadScreen(loader);
	}

	@FXML
	void zooTotalRevenue(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TotalRevenue.fxml"));
		Main.choice = "totalRevenue";
		LoadScreen(loader);

	}

	@FXML
	void zooData(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooData.fxml"));
		Main.choice = "zooData";
		LoadScreen(loader);

	}

	@FXML
	void moveVisitor(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MoveVisitor.fxml"));
		Main.choice = "moveVisitor";
		LoadScreen(loader);

	}

	void LoadScreen(FXMLLoader loader) {
		try {

			AnchorPane pane = loader.load();
			MainPanel.getChildren().clear();
			MainPanel.getChildren().add(pane);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void logOutFromSystem(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginScreen.fxml"));
		Main.choice = "logOut";
		logOut(loader);
		showAlert(AlertType.INFORMATION, "LogOut", "End Of Shift: " + LocalTime.now().getHour() + ":"
				+ LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond(), "");
	}

	private void showAlert(AlertType type, String title, String header, String text) {
		Alert alert = new Alert(type);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.setResizable(true);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		DialogPane dialogPane = alert.getDialogPane();

		dialogPane.setStyle(
				"-fx-background-image: url('/photos/pumba.jpg'); -fx-background-size: cover; -fx-font-weight: bold; -fx-font-size: 12px;");
		alert.showAndWait();
	}

	void logOut(FXMLLoader loader) {
		try {

			AnchorPane pane = loader.load();
			anchorP.getChildren().clear();
			anchorP.getChildren().add(pane);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
