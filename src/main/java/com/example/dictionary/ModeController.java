package com.example.dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ModeController {
    @FXML
    protected Button Home;

    @FXML
    protected Button Online;

    @FXML
    protected Button ManageWord;

    @FXML
    protected Button About;

    @FXML
    private void onClickButtonToHome(ActionEvent actionEvent) throws IOException {
        Parent HomePage = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene HomePage_scene = new Scene(HomePage);

        Stage HomePage_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        HomePage_stage.setScene(HomePage_scene);
        HomePage_stage.show();
    }

    @FXML
    private void onClickButtonToOnline(ActionEvent actionEvent) throws IOException {
        Parent OnlineMode = FXMLLoader.load(getClass().getResource("ModeOnline.fxml"));
        Scene OnlineMode_scene = new Scene(OnlineMode);

        Stage OnlineMode_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        OnlineMode_stage.setScene(OnlineMode_scene);
        OnlineMode_stage.show();
    }

    @FXML
    private void onClickButtonToManageWord(ActionEvent actionEvent) throws IOException {
        Parent ManageWordMode = FXMLLoader.load(getClass().getResource("Manager.fxml"));
        Scene ManageWordMode_scene = new Scene(ManageWordMode);

        Stage ManageWordMode_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        ManageWordMode_stage.setScene(ManageWordMode_scene);
        ManageWordMode_stage.show();
    }

    @FXML
    private void onClickButtonToAbout(ActionEvent actionEvent) throws IOException {
        Parent AboutMode = FXMLLoader.load(getClass().getResource("About.fxml"));
        Scene AboutMode_scene = new Scene(AboutMode);

        Stage AboutMode_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        AboutMode_stage.setScene(AboutMode_scene);
        AboutMode_stage.show();
    }
    @FXML
    private void onClickButtonToHangman(ActionEvent actionEvent) throws IOException {
        Parent HangmanMode = FXMLLoader.load(getClass().getResource("SelectWords.fxml"));
        Scene HangmanMode_scene = new Scene(HangmanMode);

        Stage HangmanMode_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        HangmanMode_stage.setScene(HangmanMode_scene);
        HangmanMode_stage.show();
    }
    public ModeController() {
    }
}
