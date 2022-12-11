package View;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import Exceptions.EmptyFieldException;
import Exceptions.UserLoginException;
import Model.Zoo;
import Model.ZooEmployee;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.DialogPane;

import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Region;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.stage.Stage;

public class LoginScreen {

	Zoo zoo = Zoo.getInstance();

	@FXML
	private AnchorPane anchorP;

	@FXML
	private TextField username;

	@FXML
	private RadioButton showPassword;

	@FXML
	private PasswordField loginPassword;

	@FXML
	private TextField passwordText;

	@FXML
	private Button LoginButton;

	private String password;

	// if the 'forgot password' button is pressed then sends a text input dialog to
	// the user
	@FXML
	void forgotPassword(ActionEvent event) {
		inputDialog();
	}

	// log in as an admin or as an employee
	@FXML
	void EnterTheMenu(ActionEvent event) {
		try {
			if (username.getText().equals("admin")
					&& (loginPassword.getText().equals("admin") || passwordText.getText().equals("admin"))) {
				showAlert(AlertType.INFORMATION, "Enter The System",
						LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + "  "
								+ LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/"
								+ LocalDate.now().getYear() + "\nWelcome Back Admin!",
						"");
				Main.whoAmI = "admin";
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AdminMain.fxml"));
				LoadScreen(loader);
				return;
			}
			if (username.getText().equals("admin") && !loginPassword.getText().equals("admin")) {
				throw new UserLoginException();
			}
			if (username.getText().isEmpty() || (loginPassword.getText().isEmpty() && passwordText.getText().isEmpty()))
				throw new EmptyFieldException();

			if (!username.getText().equals("admin")) {

				try {
					Main.empId = Integer.parseInt(username.getText());
					if (zoo.getUsernamesAndPasswords().containsKey(Main.empId) && (passwordText.getText()
							.equals(zoo.getUsernamesAndPasswords().get(Main.empId))
							|| loginPassword.getText().equals(zoo.getUsernamesAndPasswords().get(Main.empId)))) {
						ZooEmployee zooEmp = zoo.getEmployees().get(Main.empId);
						String name = zooEmp.getFirstName() + " " + zooEmp.getLastName();

						if (LocalTime.now().isAfter(LocalTime.NOON) && LocalTime.now().isBefore(LocalTime.of(18, 00))) {

							showAlert(AlertType.INFORMATION, "Welcome Back",
									"Good Afternoon " + name + "!" + "\nShift started at " + LocalTime.now().getHour()
											+ ":" + LocalTime.now().getMinute(),
									"");
						}

						else if (LocalTime.now().isBefore(LocalTime.of(12, 00))
								&& LocalTime.now().isAfter(LocalTime.of(6, 00))) {
							showAlert(AlertType.INFORMATION, "Welcome Back",
									"Good Morning " + name + "!" + "\nShift started at " + LocalTime.now().getHour()
											+ ":" + LocalTime.now().getMinute(),
									"");
						} else {
							showAlert(AlertType.INFORMATION, "Welcome Back",
									"Good Evening " + name + "!" + "\nShift started at " + LocalTime.now().getHour()
											+ ":" + LocalTime.now().getMinute(),
									"");
						}
						Main.whoAmI = String.valueOf(username.getText());
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ZooEmployeeMain.fxml"));
						LoadScreen(loader);
						return;

					}
					if (zoo.getUsernamesAndPasswords().containsKey(Main.empId)
							&& !(zoo.getUsernamesAndPasswords().get(Main.empId).equals(loginPassword.getText()))) {
						ZooEmployee zooEmp = zoo.getEmployees().get(Main.empId);
						String name = zooEmp.getFirstName() + " " + zooEmp.getLastName();
						showFailAlert(AlertType.ERROR, "Incorrect Password", null,
								"Sorry " + name + ", You entered a wrong password...");
						return;
					}
					if (!zoo.getUsernamesAndPasswords().containsKey(Main.empId)) {
						throw new UserLoginException();
					}

					if (username.getText().isEmpty() || loginPassword.getText().isEmpty())
						throw new EmptyFieldException();
				} catch (NumberFormatException n) {
					showFailAlert(AlertType.ERROR, "Error!", "Login Failed", n.getMessage());
					return;
				}
			}

		} catch (UserLoginException u) {
			showFailAlert(AlertType.ERROR, "Error!", "Login Failed", u.getMessage());
			return;
		} catch (EmptyFieldException e) {
			showFailAlert(AlertType.ERROR, "Error!", "Login Failed...\n " + e.getMessage(), "");
			return;
		}

	}

	// show the password
	@FXML
	void showThePassword(ActionEvent event) {

		if (showPassword.isSelected()) {
			password = loginPassword.getText();
			passwordText.setText(password);
			passwordText.setVisible(true);
			loginPassword.setVisible(false);
			loginPassword.setText(passwordText.getText());
			return;
		}

		loginPassword.setText(passwordText.getText());
		loginPassword.setVisible(true);
		passwordText.setVisible(false);
	}

	@FXML
	void LoadScreen(FXMLLoader loader) {
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

		alert.setHeight(Region.USE_PREF_SIZE);
		alert.setWidth(Region.USE_PREF_SIZE);
		alert.setResizable(true);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);

		DialogPane dialogPane = alert.getDialogPane();
		if (header.equals(LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + "  "
				+ LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/"
				+ LocalDate.now().getYear() + "\nWelcome Back Admin!")) {
			dialogPane.setStyle(
					"-fx-background-image: url('/photos/mufasa.jpeg'); -fx-background-size: cover; -fx-font-weight: bold; -fx-font-size: 12px;");
		} else {
			dialogPane.setStyle(
					"-fx-background-image: url('/photos/simba.jpg'); -fx-background-size: cover; -fx-font-weight: bold; -fx-font-size: 12px;");
		}
		if (title.equals("Success")) {
			File temp = new File("");
			String abPath = temp.getAbsolutePath();
			String path = new File(abPath + "/src/Media/success.mp3").getAbsolutePath();
			MediaPlayer sound = new MediaPlayer(new Media(new File(path).toURI().toString()));
			sound.play();
		} else {
			File temp = new File("");
			String abPath = temp.getAbsolutePath();
			String path = new File(abPath + "/src/Media/lionKing.mp3").getAbsolutePath();
			MediaPlayer sound = new MediaPlayer(new Media(new File(path).toURI().toString()));
			sound.play();
		}
		alert.showAndWait();
	}

	private void showFailAlert(AlertType type, String title, String header, String text) {
		Alert alert = new Alert(type);

		alert.setHeight(Region.BASELINE_OFFSET_SAME_AS_HEIGHT);
		alert.setWidth(Region.BASELINE_OFFSET_SAME_AS_HEIGHT);
		alert.setResizable(true);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		File temp = new File("");
		String abPath = temp.getAbsolutePath();
		String path = new File(abPath + "/src/Media/fail.mp3").getAbsolutePath();
		MediaPlayer sound = new MediaPlayer(new Media(new File(path).toURI().toString()));
		sound.play();
		alert.showAndWait();
	}

	// text input dialog which asks the user to enter his full name in order to
	// search him in the zoo data base
	private void inputDialog() {

		Stage s = new Stage();
		// set title for the stage
		s.setTitle("User Confirmation");

		// create a text input dialog
		TextInputDialog td = new TextInputDialog("");

		// setHeaderText
		td.setHeaderText("Enter your full name:");
		td.showAndWait();

		String result = td.getResult();
		if (result != null) {
			for (ZooEmployee emp : zoo.getEmployees().values()) {
				String fullName = emp.getFirstName() + " " + emp.getLastName();
				if (fullName.equals(result)) {
					showAlert(AlertType.INFORMATION, "Success", "Your password is: " + emp.getPassword(), "");
					return;
				}

			}
			showFailAlert(AlertType.INFORMATION, "Failed", "Sorry, could not find you in the system...", "");
			return;
		} else {
			td.close();
			return;
		}

	}

}