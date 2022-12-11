package View;

import java.io.File;
import java.io.IOException;

import Model.Zoo;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javafx.application.Application;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import javafx.stage.Stage;

public class Main extends Application {

	private Zoo zoo = Zoo.getInstance();

	static Stage mainScreen;

	static String Op = null;

	static String choice = "";

	static String whoAmI = "";

	static Integer empId = 0;

	@FXML
	private AnchorPane anchorP;

	@FXML
	private MediaView mv;

	@FXML
	private MediaPlayer mp;

	@FXML
	private Button Enter;

	@FXML
	private StackPane stackPane;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Zoo.readFile();
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Welcome.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setResizable(false);
			primaryStage.setHeight(550);
			primaryStage.setWidth(700);
			Main.mainScreen = primaryStage;
			primaryStage.setScene(scene);
			primaryStage.setTitle("Simba Zoo");
			scene.getStylesheets().add(getClass().getResource("styleSheet.css").toExternalForm());
			File temp = new File("");
			String abPath = temp.getAbsolutePath();
			String path = new File(abPath + "/src/Media/Wildlife.mp4").getAbsolutePath();
			MediaPlayer mp = new MediaPlayer(new Media(new File(path).toURI().toString()));
			mv = new MediaView(mp);
			mp.setVolume(0);
			mv.setFitHeight(primaryStage.getHeight());
			mv.setFitWidth(primaryStage.getWidth());
			mp.play();
			mp.setCycleCount(MediaPlayer.INDEFINITE);
			mv.getMediaPlayer().audioSpectrumIntervalProperty();
			root.getChildren().add(mv);

			primaryStage.getIcons().add(new Image("/photos/background (2).jpg"));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void EnterTheSystem(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginScreen.fxml"));
		LoadScreen(loader);

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


	@Override
	public void stop() {

		zoo.writeToFile();

	}
}