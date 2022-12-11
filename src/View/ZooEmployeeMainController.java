package View;

import java.io.IOException;
import java.time.LocalTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class ZooEmployeeMainController {

	@FXML
	private AnchorPane anchorP;

	@FXML
	private AnchorPane MainPanel;

	@FXML
	private Button logOutButton;

	@FXML
	private Button homeButton;

	// home page
	@FXML
	void home(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Home.fxml"));
		Main.choice = "home";
		LoadScreen(loader);
	}

	// log out
	@FXML
	void logOutFromSystem(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginScreen.fxml"));
		Main.choice = "logOut";
		logOut(loader);
		showAlert(AlertType.INFORMATION, "LogOut", "End Of Shift: " + LocalTime.now().getHour() + ":"
				+ LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond(), "");
	}

	// add Drink to the same section
	@FXML
	void addDrink(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddDrink.fxml"));
		Main.choice = "addDrink";
		LoadScreen(loader);

	}

	// add Employee to the same section
	@FXML
	void addEmployee(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddEmployee.fxml"));
		Main.choice = "addEmployee";
		LoadScreen(loader);

	}

	// add food to the same section
	@FXML
	void addFood(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddFood.fxml"));
		Main.choice = "addFood";
		LoadScreen(loader);

	}

	// add visitor
	@FXML
	void addVisitor(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddVisitor.fxml"));
		Main.choice = "addVisitor";
		LoadScreen(loader);
	}

	// query- all animals that a certain employee has taken care of
	@FXML
	void allAnimalsByWorker(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FindAllAnimalsByWorker.fxml"));
		Main.choice = "allAnimalsByWorker";
		LoadScreen(loader);

	}

	// query- all the snacks of a certain cafeteria
	@FXML
	void allSnacksBySnackBar(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AllSnacksBySnackBar.fxml"));
		LoadScreen(loader);

	}

	// query- choose a section you want to see all the animals of
	@FXML
	void getAllAnimalsBySection(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/GetAllAnimalsBySection.fxml"));
		Main.choice = "getAllAnimalsBySection";
		LoadScreen(loader);
	}

	// query- all the discounts of the visitors
	@FXML
	void getAllDiscounts(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/GetAllDiscounts.fxml"));
		Main.choice = "getAllDiscounts";
		LoadScreen(loader);
	}

	// query- get the section with the highest gap between employees and visitors
	@FXML
	void getMaxVsMin(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/GetMaxVsMin.fxml"));
		Main.choice = "getMaxVsMin";
		LoadScreen(loader);
	}

	// move a visitor to a different section
	@FXML
	void moveVisitor(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooEmployeeMoveVisitor.fxml"));
		Main.choice = "moveVisitor";
		LoadScreen(loader);

	}

	// purcahse snack of a visitor
	@FXML
	void newPurchase(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewPurchase.fxml"));
		Main.choice = "newPurchase";
		LoadScreen(loader);

	}

	// all purchases
	@FXML
	void purchases(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Purchases.fxml"));
		Main.choice = "purchases";
		LoadScreen(loader);

	}

	// ************* remove ************* //
	@FXML
	void removeEmployee(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooEmployeeRemoveEmployee2.fxml"));
		Main.choice = "removeEmployee";
		LoadScreen(loader);

	}

	@FXML
	void removeSnack(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RemoveSnack.fxml"));
		Main.choice = "removeSnack";
		LoadScreen(loader);

	}

	@FXML
	void removeVisitor(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooEmolyeeRemoveVisitor.fxml"));
		Main.choice = "removeVisitor";
		LoadScreen(loader);

	}

	// query- get al the reptiles that hibernate
	@FXML
	void reptilesSleep(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ReptilesSleep.fxml"));
		Main.choice = "reptilesSleep";
		LoadScreen(loader);
	}

	// employee treats an animal
	@FXML
	void treatAnimal(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooEmployeeAnimalsTreatment.fxml"));
		Main.choice = "";
		LoadScreen(loader);

	}

	// all Data page
	@FXML
	void zooData(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooData.fxml"));
		Main.choice = "zooData";
		LoadScreen(loader);

	}

	// revenue
	@FXML
	void zooTotalRevenue(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TotalRevenue.fxml"));
		Main.choice = "totalRevenue";
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

	void logOut(FXMLLoader loader) {
		try {

			AnchorPane pane = loader.load();
			anchorP.getChildren().clear();
			anchorP.getChildren().add(pane);

		} catch (IOException e) {
			e.printStackTrace();
		}
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

}
