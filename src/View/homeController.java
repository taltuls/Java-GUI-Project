package View;

import java.io.File;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.scene.effect.Reflection;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class homeController {

	@FXML
	private AnchorPane MainPanel;

	@FXML
	private Label homeTitle;

	@FXML
	private MediaView mv;

	@FXML
	private Button stopButton;

	@FXML
	private Button showButton;

	@FXML
	private TextArea textArea;

	// when the button is clicked the video is shown
	@FXML
	void playVideo(ActionEvent event) {
		showButton.setVisible(false);
		mv.setVisible(true);
		File temp = new File("");
		String abPath = temp.getAbsolutePath();
		String path = new File(abPath + "/src/Media/ourZoo.mp4").getAbsolutePath();
		MediaPlayer mp = new MediaPlayer(new Media(new File(path).toURI().toString()));
		mv = new MediaView(mp);
		Reflection r = new Reflection();
		r.setBottomOpacity(0.9);
		mv.setEffect(r);
		mv.setX(25);
		mv.setY(120);
		mp.setVolume(0);
		mp.play();
		stopButton.setVisible(true);
		mp.setCycleCount(MediaPlayer.INDEFINITE);
		mv.getMediaPlayer().audioSpectrumIntervalProperty();
		MainPanel.getChildren().add(mv);
	}

	// when the 'Stop' button is clicked the video stops
	@FXML
	void stopVideo(ActionEvent event) {
		mv.setVisible(false);
		stopButton.setVisible(false);
		textArea.setVisible(true);
	}

}