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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectDifficultyController implements Initializable{

	@FXML
	RadioButton level1;
	@FXML
	RadioButton level2;
	@FXML
	RadioButton level3;
	@FXML
	RadioButton level4;
	@FXML
	ToggleGroup difficulty;
	@FXML
	Label label1;
	@FXML
	Button nextButton;
	@FXML
	Button okButton;
	@FXML
	Button backButton;
	
	static int difficultyLevel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	@FXML
	public void selectingDifficulty(ActionEvent event){
		//indicated user can move on
		
		if (level1.isSelected() || level2.isSelected() || level3.isSelected() || level4.isSelected()) {
			RadioButton rb = (RadioButton) difficulty.getSelectedToggle();
			int level = Character.getNumericValue(rb.getId().charAt(5));
			FileHandling.sortWordsIntoDifficulty(level+4);
			difficultyLevel = level + 4;
			nextButton.setDisable(false);
		}
	}
	
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
		Stage secondaryStage;
		Parent root2;
		if (event.getSource() == nextButton) {
			// get reference to the button's stage
			secondaryStage = (Stage) nextButton.getScene().getWindow();
			// load up  FXML document
			root2 = FXMLLoader.load(getClass().getResource("/com/example/dictionary/TheGame.fxml"));
			// create a new scene with root and set the stage
			Scene scene2 = new Scene(root2);
			secondaryStage.setScene(scene2);
			secondaryStage.show();
		}else{
			if(event.getSource() == backButton){
				// get reference to the button's stage
				secondaryStage = (Stage) backButton.getScene().getWindow();
				// load up  FXML document
				root2 = FXMLLoader.load(getClass().getResource("/com/example/dictionary/SelectWords.fxml"));
				// create a new scene with root and set the stage
				Scene scene2 = new Scene(root2);
				secondaryStage.setScene(scene2);
				secondaryStage.show();
			}
		}
	}
	

}
