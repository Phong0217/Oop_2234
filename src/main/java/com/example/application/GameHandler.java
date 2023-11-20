package com.example.application;

import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;

public class GameHandler implements Serializable {

	private String gameWord;
	private int lifeCount = 8; // keeps track of lives
	private boolean correctLetter; // checks if right letter was selected
	private int lengthCheck = 0; // checks if the word matched with input
	private ArrayList<String> userInput = new ArrayList<String>(); // storing user input
	private int difficultyLevel;
	private boolean gLoaded = false;
	private ArrayList<String> correctInput = new ArrayList<String>();
	private ArrayList<String> incorrectInput = new ArrayList<String>();
	
	public GameHandler(){
		
	}

	public ArrayList<String> getIncorrectInput() {
		return incorrectInput;
	}


	public void setIncorrectInput(ArrayList<String> incorrectInput) {
		this.incorrectInput = incorrectInput;
	}


	public void setLengthCheck(int lengthCheck) {
		this.lengthCheck = lengthCheck;
	}


	public ArrayList<String> getCorrectInput() {
		return correctInput;
	}

	public void setCorrectInput(ArrayList<String> correctInput) {
		this.correctInput = correctInput;
	}

	public void saveGame(){
		
		GameController.newGame.setGLoaded(true);
		FileChooser fc = new FileChooser();//creating a new filechooser object
		fc.setTitle("Choose the location you wish to save your game.");
		File chosen = fc.showSaveDialog(null);
		
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(chosen.getPath() + ".ser"))) {
			outputStream.writeObject(GameController.newGame);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void loadGame(String path){
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
			GameController.newGame = (GameHandler) inputStream.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	
	public String getGameWord() {
		return gameWord;
	}

	public void setGameWord(String gameWord) {
		this.gameWord = gameWord;
	}
	
	public int getLifeCount() {
		return lifeCount;
	}
	
	public void setLifeCount(int lifeCount){
		this.lifeCount = lifeCount;
	}

	public void updateLifeCount() {
		lifeCount --;
	}

	public boolean isCorrectLetter() {
		return correctLetter;
	}

	public void setCorrectLetter(boolean correctLetter) {
		this.correctLetter = correctLetter;
	}

	public int getLengthCheck() {
		return lengthCheck;
	}

	public void updateLengthCheck() {
		this.lengthCheck ++;
	}


	public ArrayList<String> getUserInput() {
		return userInput;
	}

	public void setUserInput(ArrayList<String> userInput) {
		this.userInput = userInput;
	}


	public boolean isGLoaded() {
		return gLoaded;
	}

	public void setGLoaded(boolean gameLoaded) {
		this.gLoaded = gameLoaded;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
