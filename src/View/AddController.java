package View;

import Exceptions.DiscountCheckException;
import Exceptions.DuplicatedValues;
import Exceptions.IncompatibleDiscount;

import java.io.File;

import java.net.URL;

import java.util.ArrayList;

import java.util.ResourceBundle;

import Exceptions.EmptyFieldException;
import Exceptions.IncorrectName;

import Exceptions.ShortPasswordException;
import Model.Bird;
import Model.Drink;
import Model.Food;
import Model.Mammal;
import Model.Reptile;
import Model.Section;
import Model.SnackBar;
import Model.Visitor;
import Model.Zoo;
import Model.ZooEmployee;
import Utils.AnimalFood;
import Utils.Discount;
import Utils.Gender;
import Utils.Job;
import Utils.SnackType;
import Utils.TicketType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Region;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

public class AddController {

	Zoo zoo = Zoo.getInstance();

	private final String nullPointer = "The system cannot complete your request. Some information might be missing.";

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane MainPanel;

	@FXML
	private AnchorPane HeadLinePane;

	// employee
	@FXML
	private Label thisSection;

	@FXML
	private DatePicker birthDate;

	@FXML
	private ToggleGroup gender;

	@FXML
	private RadioButton male;

	@FXML
	private RadioButton female;

	@FXML
	private ChoiceBox<Section> empSections = new ChoiceBox<Section>();

	@FXML
	private ChoiceBox<Job> job = new ChoiceBox<Job>();

	@FXML
	private PasswordField password;

	@FXML
	private Tooltip tooltip;

	@FXML
	private Text passwordMeter;

	@FXML
	private Label sectionEmployee;

	// visitor
	@FXML
	private ChoiceBox<TicketType> ticket = new ChoiceBox<TicketType>();

	@FXML
	private TextField FirstName;

	@FXML
	private TextField lastName;

	@FXML
	private ChoiceBox<Discount> discount = new ChoiceBox<Discount>();

	// mammal
	@FXML
	private ChoiceBox<AnimalFood> animalFood = new ChoiceBox<AnimalFood>();

	@FXML
	private RadioButton yesMeatEater;

	@FXML
	private ToggleGroup meatEater;

	@FXML
	private RadioButton noMeatEater;

	// bird
	@FXML
	private RadioButton yesCanFly;

	@FXML
	private ToggleGroup canFly;

	@FXML
	private RadioButton noCanFly;

	// mammal and bird
	@FXML
	private RadioButton yesPicture;

	@FXML
	private ToggleGroup picture;

	@FXML
	private RadioButton noPicture;

	// Reptile
	@FXML
	private RadioButton notHibernates;

	@FXML
	private RadioButton notDangerous;

	@FXML
	private RadioButton yesHibernates;

	@FXML
	private RadioButton yesDangerous;

	@FXML
	private ToggleGroup hibernation;

	@FXML
	private ToggleGroup dangerous;

	// food
	@FXML
	private RadioButton yesFattening;

	@FXML
	private ToggleGroup isFattening;

	@FXML
	private ChoiceBox<String> FoodName = new ChoiceBox<String>();

	@FXML
	private RadioButton notFattening;

	@FXML
	private ChoiceBox<SnackType> FoodType = new ChoiceBox<SnackType>();

	@FXML
	private RadioButton yesPlate;

	@FXML
	private ToggleGroup plate;

	@FXML
	private RadioButton noPlate;

	@FXML
	private RadioButton yesSpicy;

	@FXML
	private ToggleGroup spicy;

	@FXML
	private RadioButton noSpicy;

	@FXML
	private ChoiceBox<SnackBar> SnackBar = new ChoiceBox<>();

	@FXML
	private TextField FoodPrice;

	@FXML
	private RadioButton yesGluten;

	@FXML
	private ToggleGroup gluten;

	@FXML
	private RadioButton noGluten;

	// drink
	@FXML
	private ToggleGroup fattening;
	@FXML
	private RadioButton yesSparkling;

	@FXML
	private ToggleGroup sparkling;

	@FXML
	private RadioButton noSparkling;

	@FXML
	private RadioButton yesStraw;

	@FXML
	private ToggleGroup straw;

	@FXML
	private RadioButton noStraw;
	@FXML
	private TextField drinkPrice;

	@FXML
	private RadioButton yasIce;

	@FXML
	private ToggleGroup ice;

	@FXML
	private RadioButton noIce;

	@FXML
	private TextField SnackName;

	// Section
	@FXML
	private TextField sectionName;

	@FXML
	private Button saveButton;

	@FXML
	private ChoiceBox<Integer> maxCapacity = new ChoiceBox<Integer>();

	// SnackBar
	@FXML
	private TextField snackBarName;

	// initialize
	@FXML
	void initialize() {

		if (Main.whoAmI != "admin") {
			if (Main.choice.equals("addEmployee")) {
				empSections.setVisible(false);
				sectionEmployee.setVisible(false);
			}
		}

		ArrayList<SnackType> snackTypes = new ArrayList<SnackType>();
		for (SnackType s : SnackType.values()) {
			snackTypes.add(s);
		}
		snackTypes.remove(SnackType.Drink);

		// all lists
		ObservableList<Integer> maxCapacities = FXCollections.observableArrayList(10, 20, 30, 40, 50);
		ObservableList<Job> allJobs = FXCollections.observableArrayList(Job.values());
		ObservableList<Section> allSections = FXCollections.observableArrayList(zoo.getSections().values());
		ObservableList<TicketType> allTicketTypes = FXCollections.observableArrayList(TicketType.values());
		ObservableList<Discount> allDiscounts = FXCollections.observableArrayList(Discount.values());
		ObservableList<AnimalFood> aFood = FXCollections.observableArrayList(AnimalFood.values());
		ObservableList<SnackType> foodTypes = FXCollections.observableArrayList(snackTypes);
		ObservableList<SnackBar> bars = FXCollections.observableArrayList(zoo.getBars().values());

		maxCapacity.getItems().addAll(maxCapacities);
		job.getItems().addAll(allJobs);
		empSections.setItems(allSections);
		ticket.getItems().addAll(allTicketTypes);
		discount.getItems().addAll(allDiscounts);
		animalFood.getItems().addAll(aFood);
		FoodType.setItems(foodTypes);
		SnackBar.setItems(bars);
	}

	// ** if the password is less than 5 characters than says its too short ** //
	@FXML
	void passwordStrength(KeyEvent eventAction) {
		try {
			if (password.getText().length() < 4) {
				throw new ShortPasswordException();
			}
			if (password.getText().length() > 4) {
				passwordMeter.setText("");
			}
		} catch (ShortPasswordException s) {
			passwordMeter.setText("Your password is too short");
			passwordMeter.setStyle(".text { \r\n" + " -fx-font-smoothing-type: lcd;\r\n" + " -fx-fill: white;\r\n"
					+ " -fx-font-weight: bold; " + "}");
			return;
		}
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
		} else if (type == AlertType.INFORMATION && header.equals("Added Successfully!")) {
			File temp = new File("");
			String abPath = temp.getAbsolutePath();
			String path = new File(abPath + "/src/Media/success.mp3").getAbsolutePath();
			MediaPlayer sound = new MediaPlayer(new Media(new File(path).toURI().toString()));
			sound.play();
		}
		alert.showAndWait();
	}

	//  ** when the button 'Save' is clicked ** //

	@FXML
	void saveData(ActionEvent event) {
		try {
			boolean res = false;
			// visitor
			if (Main.choice.equals("addVisitor")) {

				Gender visitorGender = null;
				RadioButton rb = (RadioButton) gender.getSelectedToggle();
				if (rb.getText().equals("Female")) {
					visitorGender = Gender.Female;
				} else if (rb.getText().equals("Male")) {
					visitorGender = Gender.Male;
				}

				Visitor vis = new Visitor(FirstName.getText(), lastName.getText(), birthDate.getValue(), visitorGender,
						ticket.getValue(), discount.getValue(), empSections.getValue());

				if (FirstName.getText().isEmpty() || lastName.getText().isEmpty() || birthDate.getValue() == null
						|| visitorGender == null || ticket.getValue() == null || discount.getValue() == null
						|| empSections == null)
					throw new EmptyFieldException();
				if (!FirstName.getText().matches("[a-zA-Z]+") || !lastName.getText().matches("[a-zA-Z]+"))
					throw new IncorrectName();

				if (!(ticket.getValue().equals(TicketType.Soldiar)) && discount.getValue().equals(Discount.Behatzdaa)
						|| discount.getValue().equals(Discount.Hever))
					throw new IncompatibleDiscount();
				if (!(ticket.getValue().equals(TicketType.Student))
						&& discount.getValue().equals(Discount.Haifa_Student))
					throw new IncompatibleDiscount();
				if (!(ticket.getValue().equals(TicketType.Old)) && discount.getValue().equals(Discount.Beyahad))
					throw new IncompatibleDiscount();

				for (ZooEmployee emp : zoo.getEmployees().values()) {
					if (FirstName.getText().equals(emp.getFirstName()) && lastName.getText().equals(emp.getLastName())
							&& birthDate.getValue().equals(emp.getBirthDay())) {
						throw new DuplicatedValues();
					}
				}

				res = zoo.newVisitorInZoo(vis, empSections.getValue());

				if (ticket.getValue().getValue() - vis.checkTicketPrice() > 0.25 * ticket.getValue().getValue()) {
					if (discount.getValue().equals(Discount.Hever)) {
						showAlert(AlertType.INFORMATION, "Hever Discount", null,
								"Pay Attention, The Discount is more than 25%.");
					} else {
						throw new DiscountCheckException();
					}
				}
			}

// ---------------------------------------------------------------------------------------------------------------------------------//
			// employee
			if (Main.choice.equals("addEmployee")) {
				Gender employeeGender = null;
				RadioButton rb = (RadioButton) gender.getSelectedToggle();
				if (rb.getText().equals("Female")) {
					employeeGender = Gender.Female;
				} else if (rb.getText().equals("Male")) {
					employeeGender = Gender.Male;
				}

				if (FirstName.getText().isEmpty() || lastName.getText().isEmpty() || birthDate.getValue().equals(null)
						|| employeeGender == null || job.getValue().equals(null) || password.getText().isEmpty()) {
					throw new EmptyFieldException();
				}
				if (Main.whoAmI.equals("admin") && empSections.getValue().equals(null)) {
					throw new EmptyFieldException();

				}

				if (!FirstName.getText().matches("[a-zA-Z]+") || !lastName.getText().matches("[a-zA-Z]+")) {
					throw new IncorrectName();
				}
				Section s = null;
				if (Main.whoAmI.equals("admin")) {
					s = empSections.getValue();

				}

				String firstName = FirstName.getText();
				if (Main.whoAmI != "admin") {
					s = zoo.getEmployees().get(Main.empId).getSection();
				}

				ZooEmployee emp = new ZooEmployee(firstName, lastName.getText(), birthDate.getValue(), employeeGender,
						s, job.getValue(), password.getText());

				res = zoo.addEmployee(emp);
				if (res) {
					Integer userName = Integer.valueOf(emp.getId());
					zoo.getUsernamesAndPasswords().put(userName, password.getText());

					showAlert(AlertType.INFORMATION, "New Employee", "Your username is your Id", "ID - " + emp.getId());
				}

			}
//---------------------------------------------------------------------------------------------------------------------------------//

			// section
			if (Main.choice.equals("addSection")) {

				if (sectionName.getText().isEmpty() || maxCapacity.getValue() == null) {
					throw new EmptyFieldException();
				}

				int max = Integer.parseInt(maxCapacity.getValue().toString());
				String st = sectionName.getText();

				Section s = new Section(st, max);
				res = zoo.addSection(s);

			}
//---------------------------------------------------------------------------------------------------------------------------------//	

			// add mammal
			if (Main.choice.equals("addMammal")) {

				Gender animalGender = null;
				RadioButton genderButton = (RadioButton) gender.getSelectedToggle();
				if (genderButton.getText().equals("Female")) {
					animalGender = Gender.Female;
				} else if (genderButton.getText().equals("Male")) {
					animalGender = Gender.Male;
				}

				boolean result = false;
				String eatsMeat = null;

				RadioButton meatEaterButton = (RadioButton) meatEater.getSelectedToggle();
				if (meatEaterButton.getText().equals("Yes")) {
					eatsMeat = "true";
					result = true;
				} else {
					eatsMeat = "false";
				}

				boolean result2 = false;
				String photo = null;
				RadioButton picButton = (RadioButton) picture.getSelectedToggle();
				if (picButton.getText().equals("Yes")) {
					photo = "true";
					result2 = true;
				} else {
					photo = "false";
				}

				if (FirstName.getText().isEmpty() || birthDate.getValue() == null || animalFood.getValue() == null
						|| animalGender == null || empSections.getValue() == null || eatsMeat == null
						|| photo == null) {
					throw new EmptyFieldException();
				}

				if (!FirstName.getText().matches("[a-zA-Z]+"))
					throw new IncorrectName();

				Mammal m = new Mammal(FirstName.getText(), birthDate.getValue(), animalFood.getValue(), animalGender,
						empSections.getValue(), result, result2);

				res = zoo.addMammalById(m);

			} 

// ---------------------------------------------------------------------------------------------------------------------------------//
			// bird
			if (Main.choice.equals("addBird")) {
				Gender animalGender = null;
				RadioButton genderButton = (RadioButton) gender.getSelectedToggle();
				if (genderButton.getText().equals("Female")) {
					animalGender = Gender.Female;
				} else if (genderButton.getText().equals("Male")) {
					animalGender = Gender.Male;
				}
				boolean result = false;
				String flyStr = null;
				RadioButton fly = (RadioButton) canFly.getSelectedToggle();
				if (fly.getText().equals("Yes")) {
					flyStr = "true";
					result = true;
				} else if (fly.getText().equals("No")) {
					flyStr = "false";
				}

				boolean result2 = false;
				String photo = null;
				RadioButton picButton = (RadioButton) picture.getSelectedToggle();
				if (picButton.getText().equals("Yes")) {
					photo = "true";
					result2 = true;
				} else if (picButton.getText().equals("No")) {
					photo = "false";
				}

				if (FirstName.getText().isEmpty() || birthDate.getValue() == null || animalFood.getValue() == null
						|| animalGender == null || empSections.getValue() == null || flyStr == null || photo == null) {
					throw new EmptyFieldException();
				}

				Bird b = new Bird(FirstName.getText(), birthDate.getValue(), animalFood.getValue(), animalGender,
						empSections.getValue(), result, result2);

				res = zoo.addBirdById(b);

			}
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//			
			// reptile
			if (Main.choice.equals("addReptile")) {
				Gender animalGender = null;
				RadioButton genderButton = (RadioButton) gender.getSelectedToggle();
				if (genderButton.getText().equals("Female")) {
					animalGender = Gender.Female;
				} else if (genderButton.getText().equals("Male")) {
					animalGender = Gender.Male;
				}
				boolean result = false;
				String dangerousStr = null;
				RadioButton dangerousButton = (RadioButton) dangerous.getSelectedToggle();
				if (dangerousButton.getText().equals("Yes")) {
					dangerousStr = "true";
					result = true;
				} else if (dangerousButton.getText().equals("No")) {
					dangerousStr = "false";
				}
				boolean result2 = false;
				String hibernateStr = null;
				RadioButton hibButton = (RadioButton) hibernation.getSelectedToggle();
				if (hibButton.getText().equals("Yes")) {
					hibernateStr = "true";
					result2 = true;
				} else if (hibButton.getText().equals("No")) {
					hibernateStr = "false";
				}

				if (FirstName.getText().isEmpty() || birthDate.getValue() == null || animalFood.getValue() == null
						|| animalGender == null || empSections.getValue() == null || dangerousStr == null
						|| hibernateStr == null) {
					throw new EmptyFieldException();
				}

				Reptile r = new Reptile(FirstName.getText(), birthDate.getValue(), animalFood.getValue(), animalGender,
						empSections.getValue(), result, result2);
				res = zoo.addReptileById(r);

			}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//			
			// snackBar
			if (Main.choice.equals("addSnackBar")) {

				if (snackBarName.getText().isEmpty() || empSections.getValue() == null) {
					throw new EmptyFieldException();
				}

				SnackBar sb = new SnackBar(snackBarName.getText(), empSections.getValue());

				res = zoo.addSnackBar(sb, empSections.getValue());

			}
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
			// drink
			if (Main.choice.equals("addDrink")) {
				// is fattening
				String amIFat = null;
				boolean isFattening = false;
				RadioButton isFat = (RadioButton) fattening.getSelectedToggle();
				if (isFat.getText().equals("Yes")) {
					amIFat = "true";
					isFattening = true;
				} else if (isFat.getText().equals("No")) {
					amIFat = "false";
				}

				// Sparking
				String amISparkling = null;
				boolean isSparkling = false;
				RadioButton isSpark = (RadioButton) sparkling.getSelectedToggle();
				if (isSpark.getText().equals("Yes")) {
					amISparkling = "true";
					isSparkling = true;
				} else if (isSpark.getText().equals("No")) {
					amISparkling = "false";
				}

				// straw
				String needStraw = null;
				boolean Isstraw = false;
				RadioButton strawButton = (RadioButton) straw.getSelectedToggle();
				if (strawButton.getText().equals("Yes")) {
					needStraw = "true";
					Isstraw = true;
				} else if (strawButton.getText().equals("No")) {
					needStraw = "false";
				}

				String needIce = null;
				boolean isIce = false;
				RadioButton iceButton = (RadioButton) ice.getSelectedToggle();
				if (iceButton.getText().equals("Yes")) {
					needIce = "true";
					isIce = true;
				} else if (iceButton.getText().equals("No")) {
					needIce = "false";
				}

				if (SnackName.getText().isEmpty() || drinkPrice.getText().isEmpty() || amIFat == null
						|| amISparkling == null || needStraw == null || needIce == null
						|| SnackBar.getValue() == null) {
					throw new EmptyFieldException();

				}

				Double price = Double.parseDouble(drinkPrice.getText());

				Drink d = new Drink(SnackType.Drink, SnackName.getText(), SnackBar.getValue(), isFattening, price,
						isSparkling, Isstraw, isIce);

				res = zoo.addSnack(d);

			}
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
			// add food
			if (Main.choice.equals("addFood")) {
				// is fattening
				String amIFat = "null";
				boolean isFattening1 = false;
				RadioButton isFat = (RadioButton) isFattening.getSelectedToggle();
				if (isFat.getText().equals("Yes")) {
					amIFat = "true";
					isFattening1 = true;
				} else if (isFat.getText().equals("No")) {
					amIFat = "false";
				}

				// plate
				String needPlate = null;
				boolean isPlate = false;
				RadioButton plateButton = (RadioButton) plate.getSelectedToggle();
				if (plateButton.getText().equals("Yes")) {
					needPlate = "true";
					isPlate = true;
				} else if (plateButton.getText().equals("No")) {
					needPlate = "false";
				}

				// spicy
				String iAmSpicy = null;
				boolean isSpicy = false;
				RadioButton spicyButton = (RadioButton) spicy.getSelectedToggle();
				if (spicyButton.getText().equals("Yes")) {
					iAmSpicy = "true";
					isSpicy = true;
				} else if (spicyButton.getText().equals("No")) {
					iAmSpicy = "false";
				}

				// gluten free
				String iHaveGluten = null;
				boolean isGluten = false;
				RadioButton glutenButton = (RadioButton) gluten.getSelectedToggle();
				if (glutenButton.getText().equals("Yes")) {
					iHaveGluten = "true";
					isGluten = true;
				} else if (glutenButton.getText().equals("No")) {
					iHaveGluten = "false";
				}

				if (FoodType.getValue() == null || SnackName.getText().isEmpty() || FoodPrice.getText().isEmpty()
						|| amIFat.equals("null") || needPlate == null || iAmSpicy == null || iHaveGluten == null
						|| SnackBar.getValue() == null) {
					throw new EmptyFieldException();

				}
				double price = Double.parseDouble(FoodPrice.getText());

				Food f = new Food(FoodType.getValue(), SnackName.getText(), SnackBar.getValue(), isFattening1, price,
						isPlate, isSpicy, isGluten);

				res = zoo.addSnack(f);
			}
//------------------------------------------------------------------------------------------------------------------------------------------------------------//			

			if (res)
				showAlert(AlertType.INFORMATION, "Success", "Added Successfully!", null);

			if (!res)
				showAlert(AlertType.ERROR, "Error", "Error!", "Try again please");

		} catch (EmptyFieldException e) {
			if (Main.choice.equals("addEmployee")) {
				showAlert(AlertType.ERROR, "Error", "Cannot Add Employee", e.getMessage());
				return;
			}
			if (Main.choice.equals("addVisitor")) {
				showAlert(AlertType.ERROR, "Error", "Cannot Add Visitor", e.getMessage());
				return;
			}
			if (Main.choice.equals("addMammal")) {
				showAlert(AlertType.ERROR, "Error", "Cannot Add Mammal", e.getMessage());
				return;
			}
			if (Main.choice.equals("addReptile")) {
				showAlert(AlertType.ERROR, "Error", "Cannot Add Reptile", e.getMessage());
				return;
			}
			if (Main.choice.equals("addBird")) {
				showAlert(AlertType.ERROR, "Error", "Cannot Add Bird", e.getMessage());
				return;
			}
			if (Main.choice.equals("addSnackBar")) {
				showAlert(AlertType.ERROR, "Error", "Cannot Add SnackBar", e.getMessage());
				return;
			}
			if (Main.choice.equals("addSection")) {
				showAlert(AlertType.ERROR, "Error", "Cannot Add Section", e.getMessage());
				return;
			}

		} catch (IncorrectName i) {
			if (Main.choice.equals("addVisitor")) {
				showAlert(AlertType.ERROR, "Error", "Cannot Add visitor", i.getMessage());
				return;
			}
			if (Main.choice.equals("addEmployee")) {
				showAlert(AlertType.ERROR, "Error", "Cannot Add Employee", i.getMessage());
				return;
			}
			if (Main.choice.equals("addSection")) {
				showAlert(AlertType.ERROR, "Error", "Cannot Add Section", i.getMessage());
				return;
			}

		} catch (NumberFormatException num) {
			showAlert(AlertType.ERROR, "Status Error", "Invalid Status!", "Status has to be numbers only!");
			return;
		} catch (NullPointerException e) {
			showAlert(AlertType.ERROR, "Error", "System Error", nullPointer);
			return;
		} catch (DiscountCheckException d) {
			showAlert(AlertType.ERROR, "Error", "Discount Exception", d.getMessage());
		} catch (IncompatibleDiscount i) {
			showAlert(AlertType.ERROR, "Error", "Incompatible Discount", i.getMessage());
		} catch (DuplicatedValues d) {
			showAlert(AlertType.ERROR, "Error", "Duplicated Values", d.getMessage());
		}

	}

}
