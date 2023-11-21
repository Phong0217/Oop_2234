package com.example.dictionary;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Dictionary extends Application {

    private static final String DATA_FILE_PATH = "OOP_demo\\data\\E_V.txt";
    //private static final String FXML_FILE_PATH = "./src/main/resources/com/example/dictionary/dictionary-view.fxml";
    private static final String DATAHis_FILE_PATH = "OOP_demo\\data\\RecentList.txt";
    private static final String SPLITTING_CHARACTERS = "<html>";
    @FXML
    protected TextField searchField;
    private Map<String, Word> data = new HashMap<>();



    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Dictionary");
            //primaryStage.getIcons().add(new Image("main/resources/com/example/photo/HienThi.png"));
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public static void main(String[] args) {
        launch(args);
    }
}
