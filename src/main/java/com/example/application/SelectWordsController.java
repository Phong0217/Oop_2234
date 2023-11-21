package com.example.application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SelectWordsController implements Initializable {

	@FXML
	RadioButton sequentialButton;
	@FXML
	RadioButton parallelButton;
	@FXML
	ToggleGroup modes;
	@FXML
	Label label1;
	@FXML
	Label label2;
	@FXML
	Button nextButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@FXML
	public void chooseMode(ActionEvent event) {
		GameController.newGame = new GameHandler();
		String message = " ";
		// Will make it clear to user that they can move onto the next stage
		if (sequentialButton.isSelected() || parallelButton.isSelected()) {
			// nextButton.setOpacity(1.0);
			nextButton.setDisable(false);
		}

		// if sequential radio button is selected
		if (sequentialButton.isSelected()) {
			long startTime = System.currentTimeMillis();
			try {
				FileHandling.sequential();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			long stoppedTime = System.currentTimeMillis();
			long elapseTime = stoppedTime - startTime;
			message += "Sequential time is - " + elapseTime;
		}

		// if parallel button is selected
		if (parallelButton.isSelected()) {
			long startTime2 = System.currentTimeMillis();
			Parallel.parallel();
			long stoppedTime2 = System.currentTimeMillis();
			long elapseTime2 = stoppedTime2 - startTime2;
			message += "Parallel time is - " + elapseTime2;
		}
		// depending on which radio button is selected, message will display
		label1.setText(message);
	}

	@FXML
	private void handleButtonAction() throws IOException {
		try {
//			FXMLLoader fxmlLoader = new FXMLLoader();
//			Stage secondaryStage = (Stage) nextButton.getScene().getWindow();
//			Parent root2 = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("SelectDifficulty.fxml")));
//
//			Scene scene2 = new Scene(root2);
//			secondaryStage.setScene(scene2);
//			secondaryStage.show();
			Stage primaryStage = (Stage) nextButton.getScene().getWindow();
			FXMLLoader fxmlLoader = new FXMLLoader();

			// Load the FXML file using a relative path
			// Make sure the FXML file is in the correct location relative to your classpath
			AnchorPane root = fxmlLoader.load(getClass().getResource("/com/example/dictionary/SelectDifficulty.fxml"));

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();  // Print the IOException
		} catch (Exception e) {
			// Catch any other exception during FXMLLoader.load() or controller initialization
			e.printStackTrace();
		}

	}

}
