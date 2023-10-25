package com.example.dictionary;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable  {

    @FXML
    private AnchorPane mainContent;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane translatePane;
    @FXML
    private AnchorPane bookmarkPane;
    @FXML
    private AnchorPane historyPane;
    @FXML
    private AnchorPane settingPane;
    @FXML
    public Button translateButton;
    public Button searchButton;
    public Button bookmarkButton;
    public Button mainHistoryButton;

    @FXML
    private SearchController searchController;


    public void showSearchPane(ActionEvent actionEvent) {
    }

    public void showTranslatePane(ActionEvent actionEvent) {
    }

    public void showBookmarkPane(ActionEvent actionEvent) {
    }

    public void showHistoryPane(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
            searchPane = loader.load();
            searchController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
