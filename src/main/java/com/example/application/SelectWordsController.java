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
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
	private void handleButtonAction(ActionEvent event) throws IOException {
		Stage secondaryStage;
		Parent root2;

		// get reference to the button's stage
		secondaryStage = (Stage) nextButton.getScene().getWindow();
		// load up FXML document
		root2 = FXMLLoader.load(getClass().getResource("/com/example/dictionary/SelectDifficulty.fxml"));
		// create a new scene with root and set the stage
		Scene scene2 = new Scene(root2);
		secondaryStage.setScene(scene2);
		secondaryStage.show();

	}

}
