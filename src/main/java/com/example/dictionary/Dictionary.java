package com.example.dictionary;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dictionary extends Application {

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

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Dictionary");
            //primaryStage.getIcons().add(new Image("main/resources/com/example/photo/HienThi.png"));
            primaryStage.setScene(scene);
            primaryStage.show();

            // init components
            initComponents(scene);

            // read word list from E_V.txt
            readData();

            // load word list to the ListView
            loadWordList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initComponents(Scene scene) {
        this.definitionView = (WebView) scene.lookup("#definitionView");
        this.listView = (ListView<String>) scene.lookup("#listView");
        Dictionary context = this;
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




    public static void main(String[] args) {
        launch(args);
    }
}
