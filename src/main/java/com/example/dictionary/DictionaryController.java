package com.example.dictionary;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.concurrent.Task;

import java.io.*;
import java.util.*;

public class DictionaryController extends Application{

    private static final String DATA_FILE_PATH = "C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\E_V.txt";
    //private static final String FXML_FILE_PATH = "./src/main/resources/com/example/dictionary/dictionary-view.fxml";
    private static final String DATAHis_FILE_PATH = "C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\RecentList.txt";
    private static final String SPLITTING_CHARACTERS = "<html>";
    @FXML
    protected TextField searchField;
    private Map<String, Word> data = new HashMap<>();

    @FXML
    protected ListView<String> listView;
    @FXML
    protected WebView definitionView;




    public static void main(String[] args) {
        Application.launch(args);
    }


    //protected static NewDictionary evDic = new NewDictionary(DATA_FILE_PATH, DATAHis_FILE_PATH);
//    public NewDictionary getCurrentDic() {
//        return evDic;
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        //FileInputStream fis = new FileInputStream(FXML_FILE_PATH);
        //AnchorPane root = fxmlLoader.load(fis);
        AnchorPane root = fxmlLoader.load(getClass().getResourceAsStream("dictionary-view.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dictionary Demonstration");
        primaryStage.show();

        // init components
        initComponents(scene);

        // read word list from E_V.txt
        readData();

        // load word list to the ListView
        loadWordList();
    }

    public void initComponents(Scene scene) {
        this.definitionView = (WebView) scene.lookup("#definitionView");
        this.listView = (ListView<String>) scene.lookup("#listView");
        DictionaryController context = this;
        this.listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Word selectedWord = data.get(newValue.trim());
                    String definition = selectedWord.getDef();
                    context.definitionView.getEngine().loadContent(definition, "text/html");
                }
        );
    }

    public void loadWordList() {
        this.listView.getItems().addAll(data.keySet());
    }

    public void readData() throws IOException {
        FileReader fis = new FileReader(DATA_FILE_PATH);
        BufferedReader br = new BufferedReader(fis);
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(SPLITTING_CHARACTERS);
            String word = parts[0];
            String definition = SPLITTING_CHARACTERS + parts[1];
            Word wordObj = new Word(word, definition);
            data.put(word, wordObj);
        }


    }

    private final ArrayList<Word> searchWordTemp = new ArrayList<>();
    protected final ObservableList<String> searchList = FXCollections.observableArrayList();

//    public void searchFieldAction() throws IOException {
//        DictionaryController context = this;
//        searchWordTemp.clear();
//        searchList.clear();
//        String word = context.searchField.getText();
//        NewDictionary evDic = new NewDictionary(DATA_FILE_PATH, DATAHis_FILE_PATH);
//        int index = evDic.binaryLookup(0, evDic.getVocab().size() - 1, word, evDic.getVocab());
//        if (index < 0) {
//            Spelling corrector = new Spelling("C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\spelling.txt");
//            word = corrector.correct(word);
//            index = evDic.binaryLookup(0, evDic.getVocab().size() -1, word, evDic.getVocab());
//        }
//        updateWordInListView(word, index, evDic.getVocab(), searchWordTemp);
//        setSearchListViewItem();
//
//    }

    public void searchFieldAction() {

        DictionaryController context = this;

        // Create a Task for the search operation
        Task<Void> searchTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                searchWordTemp.clear();
                searchList.clear();

                String word = context.searchField.getText();
                // Perform the search operation
                NewDictionary evDic = new NewDictionary(DATA_FILE_PATH, DATAHis_FILE_PATH);
                int index = evDic.binaryLookup(0, evDic.getVocab().size() - 1, word, evDic.getVocab());

                if (index < 0) {
                    Spelling corrector = new Spelling("C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\spelling.txt");
                    word = corrector.correct(word);
                    index = evDic.binaryLookup(0, evDic.getVocab().size() - 1, word, evDic.getVocab());
                }

                // Update searchWordTemp with the search results
                updateWordInListView(word, index, evDic.getVocab(), searchWordTemp);
                return null;
            }
        };

        // Set the event handler for successful completion of the search task
        searchTask.setOnSucceeded(event -> {
            // Update the ListView on the JavaFX Application Thread
            setSearchListViewItem();
        });

        // Set the event handler for any exception during the search task
        searchTask.setOnFailed(event -> {
            // Handle the exception if needed
            searchTask.getException().printStackTrace();
        });

        // Run the search task in a background thread
        new Thread(searchTask).start();
    }

    public void updateWordInListView(String word, int index, ArrayList<Word> res, ArrayList<Word> des) {
        if (index < 0) {
            return;
        }
        int j = index;


        while (j >= 0) {
            if (DictionaryManager.isContain(word, res.get(j).getWord()) == 0) {
                j--;
            } else {
                break;
            }
        }
        for (int i = j + 1; i <= index; i++) {
            Word temp = new Word(res.get(i).getWord(), res.get(i).getDef());
            des.add(temp);
        }

        for (int i = index + 1; i < res.size(); i++) {
            if (DictionaryManager.isContain(word, res.get(i).getDef()) == 0) {
                Word temp = new Word(res.get(i).getWord(), res.get(i).getDef());
                des.add(temp);
            } else {
                break;
            }
        }
    }

    public void setSearchListViewItem() {
        searchList.clear();
        if (searchField.getText().equals("")) {
            searchWordTemp.clear();
            NewDictionary evDic = new NewDictionary(DATA_FILE_PATH, DATAHis_FILE_PATH);
            searchWordTemp.addAll(evDic.getVocab());
        }
        for (Word temp : searchWordTemp) {
            searchList.add(temp.getWord());
        }
        listView.setItems(searchList);
    }
    
}