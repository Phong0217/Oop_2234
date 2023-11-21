package com.example.application;

import java.io.*;
import java.util.*;

public class FileHandling {
	
	// locating files on laptop
	static File file1 = new File("C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\file1.txt");
	static File file2 = new File("C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\file2.txt");
	static File file3 = new File("C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\file3.txt");
	static File file4 = new File("C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\file4.txt");

	// variables which contain the words
	static ArrayList<String> wordsInF1 = new ArrayList<String>();
	static ArrayList<String> wordsInF2 = new ArrayList<String>();
	static ArrayList<String> wordsInF3 = new ArrayList<String>();
	static ArrayList<String> wordsInF4 = new ArrayList<String>();
	static ArrayList<ArrayList<String>> masterList = new ArrayList<ArrayList<String>>();
	static ArrayList<String> gameWords = new ArrayList<String>();
	
	static String gameWord = " ";
	
	
	public FileHandling() {
		
	}

	public static ArrayList<String> chooseRandomWords(File file, ArrayList<String> wordsInFile)
			throws FileNotFoundException {
		// reading each file and placing all words in each ArrayList
		String line = null;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			while ((line = br.readLine()) != null) {
				wordsInFile.add(line);
			}

		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

		// below is the process of choosing the fifty words, the method removes
		// words until 50 left
		Random r = new Random();
		while (wordsInFile.size() > 50) {
			int index = r.nextInt(wordsInFile.size());
			wordsInFile.remove(index);
		}

		return wordsInFile;
	}

	public static void serializeArray(ArrayList<String> masterList) throws FileNotFoundException, IOException {

		try (ObjectOutputStream outputSteam = new ObjectOutputStream(new FileOutputStream("serialized.bin"))) {
			outputSteam.writeObject(masterList);
			outputSteam.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void sequential() throws FileNotFoundException {
		//choosing 50 random words from all 4 files
		wordsInF1 = FileHandling.chooseRandomWords(file1, wordsInF1);
		wordsInF2 = FileHandling.chooseRandomWords(file2, wordsInF2);
		wordsInF3 = FileHandling.chooseRandomWords(file3, wordsInF3);
		wordsInF4 = FileHandling.chooseRandomWords(file4, wordsInF4);
		//adding all 200 words into one main list
		masterList.add(wordsInF1);
		masterList.add(wordsInF2);
		masterList.add(wordsInF3);
		masterList.add(wordsInF4);

	}
	
	public static void sortWordsIntoDifficulty(int level){
		gameWords.clear();
		for (ArrayList<String> list : masterList) {
		    for (String word : list) { 
		        if (word.length() == level){
		        	gameWords.add(word);
		        } 
		    }
		}
		
	}
	
	public static String randomWord(){
		
		Random r = new Random();
		int index = r.nextInt(gameWords.size());
		int count = 0;
		
		for(String word : gameWords){
			count ++;
			if(count == index){
				gameWord = word;
			}
		}	
		
		return gameWord;
	}
	
	public static void main(String[] args) throws IOException {

//		ThreadingOne.parallel();
//		System.out.println(wordsInF1);
//		//System.out.println(masterList);
//		int count = 0;
//		for (ArrayList<String> list : masterList) {
//		    for (String string : list) { 
//		        if (list != null) count++;
//		    }
//		}
//		System.out.println(wordsInF1.size());
//
//		System.out.println(count);	
		
//		sequential();
//		sortWordsIntoDifficulty(8);
//		System.out.print(gameWords.size());		
//		randomWord();
//		System.out.println(gameWord);
		
	}

}