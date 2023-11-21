package com.example.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameController {

	// letters in the alphabet
	@FXML
	private Button a;
	@FXML
	private Button b;
	@FXML
	private Button c;
	@FXML
	private Button d;
	@FXML
	private Button e;
	@FXML
	private Button f;
	@FXML
	private Button g;
	@FXML
	private Button h;
	@FXML
	private Button i;
	@FXML
	private Button j;
	@FXML
	private Button k;
	@FXML
	private Button l;
	@FXML
	private Button m;
	@FXML
	private Button n;
	@FXML
	private Button o;
	@FXML
	private Button p;
	@FXML
	private Button q;
	@FXML
	private Button r;
	@FXML
	private Button s;
	@FXML
	private Button t;
	@FXML
	private Button u;
	@FXML
	private Button v;
	@FXML
	private Button w;
	@FXML
	private Button x;
	@FXML
	private Button y;
	@FXML
	private Button z;

	// Hangman parts
	@FXML
	private Shape bottomStand; // life1
	@FXML
	private Shape verticalStand;// life1
	@FXML
	private Shape horizontalBeam;// life1
	@FXML
	private Shape support;// life1
	@FXML
	private Shape noose;// 2
	@FXML
	private Shape head;// 3
	@FXML
	private Shape body;// 4
	@FXML
	private Shape leftArm;// 5
	@FXML
	private Shape rightArm;// 6
	@FXML
	private Shape leftLeg;// 7
	@FXML
	private Shape rightLeg;// 8

	// variables for the letter and placeholder
	@FXML
	private Label lab1;
	@FXML
	private Label lab2;
	@FXML
	private Label lab3;
	@FXML
	private Label lab4;
	@FXML
	private Label lab5;
	@FXML
	private Label lab6;
	@FXML
	private Label lab7;
	@FXML
	private Label lab8;

	@FXML
	private Shape place1;
	@FXML
	private Shape place2;
	@FXML
	private Shape place3;
	@FXML
	private Shape place4;
	@FXML
	private Shape place5;
	@FXML
	private Shape place6;
	@FXML
	private Shape place7;
	@FXML
	private Shape place8;

	@FXML
	private Label lifeCount; // tells user how many lives are remaining
	@FXML
	private Label endMessage;// used when game is finished and ended.
	@FXML
	private Label incorrectLetters;//tells user which letters are incorrect

	// Navigation
	@FXML
	Button backButton;
	@FXML
	Button loadGame;
	@FXML
	Button saveGame;
	@FXML
	Button playButton;
	@FXML
	Button resumeButton;
	@FXML
	Button newHangmanGame;
	@FXML
	private Button playAgain;

	// non FXMl variables
	static Label[] labelArray; // storing labels in an array
	static Button[] alphabet; // alphabet array
	private ArrayList<String> temp = new ArrayList<String>(); //labels
	private ArrayList<String> temp2 = new ArrayList<String>(); //alphabet
	private ArrayList<String> temp3 = new ArrayList<String>(); //incorrect input

	public static GameHandler newGame; // GameHandler object created in
								// SelectWordsController

	public void storeInArray() {

		labelArray = new Label[8];
		labelArray[0] = lab1;
		labelArray[1] = lab2;
		labelArray[2] = lab3;
		labelArray[3] = lab4;
		labelArray[4] = lab5;
		labelArray[5] = lab6;
		labelArray[6] = lab7;
		labelArray[7] = lab8;

		alphabet = new Button[26];
		alphabet[0] = a;
		alphabet[1] = b;
		alphabet[2] = c;
		alphabet[3] = d;
		alphabet[4] = e;
		alphabet[5] = f;
		alphabet[6] = g;
		alphabet[7] = h;
		alphabet[8] = i;
		alphabet[9] = j;
		alphabet[10] = k;
		alphabet[11] = l;
		alphabet[12] = m;
		alphabet[13] = n;
		alphabet[14] = o;
		alphabet[15] = p;
		alphabet[16] = q;
		alphabet[17] = r;
		alphabet[18] = s;
		alphabet[19] = t;
		alphabet[20] = u;
		alphabet[21] = v;
		alphabet[22] = w;
		alphabet[23] = x;
		alphabet[24] = y;
		alphabet[25] = z;

	}

	public void stringToLetters(String gWord) {
		//places each letter of game word in a label
		for (int i = 0; i < gWord.length(); i++) {
			String tempChar = ""; // store letter
			tempChar += gWord.charAt(i);// store char in String
			
			if (i == 0) {
				lab1.setText(tempChar);
			} else if (i == 1) {
				lab2.setText(tempChar);
			} else if (i == 2) {
				lab3.setText(tempChar);
			} else if (i == 3) {
				lab4.setText(tempChar);
			} else if (i == 4) {
				lab5.setText(tempChar);
			} else if (i == 5) {
				lab6.setText(tempChar);
			} else if (i == 6) {
				lab7.setText(tempChar);
			} else if (i == 7) {
				lab8.setText(tempChar);
			}
		}
		System.out.println(gWord);
	}

	@FXML
	public void playGame(ActionEvent event) {
		if (event.getSource() == playButton || event.getSource() == playAgain) {

			newGame.setGameWord(FileHandling.randomWord());
			newGame.setLifeCount(8);// setting number of lives
			newGame.setLengthCheck(0);
			playButton.setVisible(false);
			endMessage.setText(" "); // keep end message blank
			incorrectLetters.setText(" "); // keep incorrectLetters blank
			newGame.setDifficultyLevel(SelectDifficultyController.difficultyLevel);
			
			//clearing arrayLists
			temp.clear();
			temp2.clear();
			temp3.clear();
			newGame.getCorrectInput().clear();
			newGame.getUserInput().clear();
			newGame.getIncorrectInput().clear();

			// turning place holders invisible depending on difficulty
			if (newGame.getDifficultyLevel() == 5) {
				place6.setOpacity(0);
				place7.setOpacity(0);
				place8.setOpacity(0);
			} else if (newGame.getDifficultyLevel() == 6) {
				place7.setOpacity(0);
				place8.setOpacity(0);
			} else if (newGame.getDifficultyLevel() == 7) {
				place8.setOpacity(0);
			}

			// storing labels and alphabet in an array
			storeInArray();

			// setting labels containing world letters invisible
			for (int i = 0; i < labelArray.length; i++) {
				labelArray[i].setVisible(false);
			}

			// setting alphabet back to normal
			for (int i = 0; i < alphabet.length; i++) {
				alphabet[i].setVisible(true);
			}

			// setting the hangman graphics invisible
			bottomStand.setVisible(false);
			verticalStand.setVisible(false);
			horizontalBeam.setVisible(false);
			support.setVisible(false);
			noose.setVisible(false);
			head.setVisible(false);
			body.setVisible(false);
			leftArm.setVisible(false);
			rightArm.setVisible(false);
			leftLeg.setVisible(false);
			rightLeg.setVisible(false);

			// Separating the letters storing in labels
			stringToLetters(newGame.getGameWord());
			// setting life counter on GUI
			lifeCount.setText("8");
		}

	}

	@FXML
	public void chooseLetter(ActionEvent event) {

		newGame.setCorrectLetter(false);

		Button letter = (Button) event.getSource(); // gets the button
		char selectedLetter = letter.getText().charAt(0);// gets char value
		// temp word variables
		int gWordLength = newGame.getGameWord().length();
		String gWord = newGame.getGameWord();

		temp.add(String.valueOf(selectedLetter));// for userInput Array
		newGame.setUserInput(temp);

		// if letter's correct
		for (int i = 0; i < gWordLength; i++) {
			if (gWord.charAt(i) == selectedLetter) {

				temp2.add(String.valueOf(selectedLetter));
				newGame.setCorrectInput(temp2);// for correct userInput
				labelArray[i].setVisible(true);
				letter.setVisible(false);
				newGame.setCorrectLetter(true);
				newGame.updateLengthCheck();

			} else {
				letter.setVisible(false);// if letter is incorrect
			}
		}

		// if a letter is incorrect
		if (newGame.isCorrectLetter() == false) {
			//storing incorrect letters
			temp3 = new ArrayList<String>();
			temp3 = newGame.getIncorrectInput();
			temp3.add(String.valueOf(selectedLetter));
			newGame.setIncorrectInput(temp3);
			
			if (newGame.getLifeCount() == 8) {
				bottomStand.setVisible(true);
				verticalStand.setVisible(true);
				horizontalBeam.setVisible(true);
				support.setVisible(true);
				newGame.updateLifeCount();
				lifeCount.setText(String.valueOf(newGame.getLifeCount()));
			} else if (newGame.getLifeCount() == 7) {
				noose.setVisible(true);
				newGame.updateLifeCount();
				lifeCount.setText(String.valueOf(newGame.getLifeCount()));
			} else if (newGame.getLifeCount() == 6) {
				head.setVisible(true);
				newGame.updateLifeCount();
				lifeCount.setText(String.valueOf(newGame.getLifeCount()));
			} else if (newGame.getLifeCount() == 5) {
				body.setVisible(true);
				newGame.updateLifeCount();
				lifeCount.setText(String.valueOf(newGame.getLifeCount()));
			} else if (newGame.getLifeCount() == 4) {
				rightArm.setVisible(true);
				newGame.updateLifeCount();
				lifeCount.setText(String.valueOf(newGame.getLifeCount()));
			} else if (newGame.getLifeCount() == 3) {
				leftArm.setVisible(true);
				newGame.updateLifeCount();
				lifeCount.setText(String.valueOf(newGame.getLifeCount()));
			} else if (newGame.getLifeCount() == 2) {
				rightLeg.setVisible(true);
				newGame.updateLifeCount();
				lifeCount.setText(String.valueOf(newGame.getLifeCount()));
			} else if (newGame.getLifeCount() == 1) {
				leftLeg.setVisible(true);
				newGame.updateLifeCount();
				lifeCount.setText(String.valueOf(newGame.getLifeCount()));
			}
			
			incorrectLetters.setText("Incorrect Letters: " + newGame.getIncorrectInput().toString() );
			
		}

		// to check if the game has ended
		if (newGame.getLengthCheck() == newGame.getDifficultyLevel() || newGame.getLifeCount() == 0) {

			System.out.println("game over");

			// winning scenario
			if (newGame.getLengthCheck() == newGame.getDifficultyLevel()) {
				endMessage.setText("You win the word was " + newGame.getGameWord());
				playAgain.setVisible(true);

				// Making all letters invisible and disabled for Play Again
				for (int i = 0; i < alphabet.length; i++) {
					alphabet[i].setVisible(false);
				}
			}

			if (newGame.getLifeCount() == 0) {// use all lives
				endMessage.setText("You lose the word was " + newGame.getGameWord());
				playAgain.setVisible(true);
				// Making all letters invisible and disabled for Play Again
				for (int i = 0; i < alphabet.length; i++) {
					alphabet[i].setVisible(false);
				}
			}
		}

	}

	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
		Stage secondaryStage;
		Parent root2;
		if (event.getSource() == backButton) {
			// get reference to the button's stage
			secondaryStage = (Stage) backButton.getScene().getWindow();
			// load up FXML document
			root2 = FXMLLoader.load(getClass().getResource("/com/example/dictionary/SelectDifficulty.fxml"));
			// create a new scene with root and set the stage
			Scene scene2 = new Scene(root2);
			secondaryStage.setScene(scene2);
			secondaryStage.show();
		} else if (event.getSource() == saveGame) {
			newGame.saveGame();
		} else if (event.getSource() == loadGame) {
			FileChooser fc = new FileChooser();
			fc.setTitle("Choose your saved file.");
			File f = fc.showOpenDialog(null);
			newGame.loadGame(f.getPath());
			resumeButton.setVisible(true);
			playButton.setVisible(false);
		} else if (event.getSource() == newHangmanGame) {
			// get reference to the button's stage
			secondaryStage = (Stage) backButton.getScene().getWindow();
			// load up FXML document
			root2 = FXMLLoader.load(getClass().getResource("/com/example/dictionary/SelectWords.fxml"));
			// create a new scene with root and set the stage
			Scene scene2 = new Scene(root2);
			secondaryStage.setScene(scene2);
			secondaryStage.show();
		}
	}

	public void resumeGame(ActionEvent event) {
		System.out.println(newGame.getGameWord());
		endMessage.setText(" "); // keep end message blank
		storeInArray();

		if (newGame.isGLoaded() == true) {

			// setting life counter
			lifeCount.setText(String.valueOf(newGame.getLifeCount()));

			// turning place holders invisible depending on difficulty
			if (newGame.getDifficultyLevel() == 5) {
				place6.setOpacity(0);
				place7.setOpacity(0);
				place8.setOpacity(0);
			} else if (newGame.getDifficultyLevel() == 6) {
				place7.setOpacity(0);
				place8.setOpacity(0);
			} else if (newGame.getDifficultyLevel() == 7) {
				place8.setOpacity(0);
			}

			// setting the hangman graphics invisible
			bottomStand.setVisible(false);
			verticalStand.setVisible(false);
			horizontalBeam.setVisible(false);
			support.setVisible(false);
			noose.setVisible(false);
			head.setVisible(false);
			body.setVisible(false);
			leftArm.setVisible(false);
			rightArm.setVisible(false);
			leftLeg.setVisible(false);
			rightLeg.setVisible(false);

			// setting the hangman graphics visible based on lives for load game
			if (newGame.getLifeCount() == 7) {
				bottomStand.setVisible(true);
				verticalStand.setVisible(true);
				horizontalBeam.setVisible(true);
				support.setVisible(true);
				noose.setVisible(true);
				lifeCount.setText(String.valueOf(newGame.getLifeCount()));
			} else if (newGame.getLifeCount() == 6) {
				bottomStand.setVisible(true);
				verticalStand.setVisible(true);
				horizontalBeam.setVisible(true);
				support.setVisible(true);
				noose.setVisible(true);
				head.setVisible(true);
				lifeCount.setText(String.valueOf(newGame.getLifeCount()));
			} else if (newGame.getLifeCount() == 5) {
				bottomStand.setVisible(true);
				verticalStand.setVisible(true);
				horizontalBeam.setVisible(true);
				support.setVisible(true);
				noose.setVisible(true);
				head.setVisible(true);
				body.setVisible(true);
				lifeCount.setText(String.valueOf(newGame.getLifeCount()));
			} else if (newGame.getLifeCount() == 4) {
				bottomStand.setVisible(true);
				verticalStand.setVisible(true);
				horizontalBeam.setVisible(true);
				support.setVisible(true);
				noose.setVisible(true);
				head.setVisible(true);
				body.setVisible(true);
				rightArm.setVisible(true);
				lifeCount.setText(String.valueOf(newGame.getLifeCount()));
			} else if (newGame.getLifeCount() == 3) {
				bottomStand.setVisible(true);
				verticalStand.setVisible(true);
				horizontalBeam.setVisible(true);
				support.setVisible(true);
				noose.setVisible(true);
				head.setVisible(true);
				body.setVisible(true);
				rightArm.setVisible(true);
				leftArm.setVisible(true);
				lifeCount.setText(String.valueOf(newGame.getLifeCount()));
			} else if (newGame.getLifeCount() == 2) {
				bottomStand.setVisible(true);
				verticalStand.setVisible(true);
				horizontalBeam.setVisible(true);
				support.setVisible(true);
				noose.setVisible(true);
				head.setVisible(true);
				body.setVisible(true);
				rightArm.setVisible(true);
				leftArm.setVisible(true);
				rightLeg.setVisible(true);
				lifeCount.setText(String.valueOf(newGame.getLifeCount()));
			} else if (newGame.getLifeCount() == 1) {
				bottomStand.setVisible(true);
				verticalStand.setVisible(true);
				horizontalBeam.setVisible(true);
				support.setVisible(true);
				noose.setVisible(true);
				head.setVisible(true);
				body.setVisible(true);
				rightArm.setVisible(true);
				leftArm.setVisible(true);
				rightLeg.setVisible(true);
				// leftLeg.setVisible(true);
				lifeCount.setText(String.valueOf(newGame.getLifeCount()));
			} else if (newGame.getLifeCount() == 0) {
				bottomStand.setVisible(true);
				verticalStand.setVisible(true);
				horizontalBeam.setVisible(true);
				support.setVisible(true);
				noose.setVisible(true);
				head.setVisible(true);
				body.setVisible(true);
				rightArm.setVisible(true);
				leftArm.setVisible(true);
				rightLeg.setVisible(true);
				leftLeg.setVisible(true);
				endMessage.setText("You lose the word was " + newGame.getGameWord());
			}
			
			//checking if a winning game was saved
			if(newGame.getLengthCheck() == newGame.getGameWord().length()){
				endMessage.setText("You win the word was " + newGame.getGameWord());
				
				for(int i = 0; i < alphabet.length; i++){
					alphabet[i].setVisible(false);
				}
				
			}

			// setting labels containing word letters invisible
			for (int i = 0; i < labelArray.length; i++) {
				labelArray[i].setVisible(false);
			}

			// setting alphabet visible
			for (int i = 0; i < alphabet.length; i++) {
				alphabet[i].setVisible(true);
			}

			// setting used letters not visible
			for (int i = 0; i < newGame.getUserInput().size(); i++) {
				String input = newGame.getUserInput().get(i);
				for (int j = 0; j < alphabet.length; j++) {
					String button = alphabet[j].getId();
					if (button.equals(input)) {
						alphabet[j].setVisible(false);
					}
				}
			}

			// setting the labels visible depending correct letters previously
			// chosen
			stringToLetters(newGame.getGameWord());
			// turning labels visible if selected previously
			for (int i = 0; i < newGame.getCorrectInput().size(); i++) {
				String input = newGame.getCorrectInput().get(i);
				for (int j = 0; j < labelArray.length; j++) {
					String label = labelArray[j].getText();
					if (input.equals(label)) {
						labelArray[j].setVisible(true);
					}
				}
			}
			
			incorrectLetters.setText("Incorrect Letters: " + newGame.getIncorrectInput().toString() );
		}
		resumeButton.setVisible(false); // not needed after method has been run
	}

}
