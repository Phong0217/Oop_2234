package com.example.dictionary;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javax.speech.EngineException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController extends DictionaryManager implements Initializable  {
    @FXML
    protected JFXHamburger hamburger;
    @FXML
    protected JFXDrawer drawer;
    @FXML
    protected TextField enWord;
    @FXML
    protected TextArea viWord;

    public void onClickSpeakerButtonEn(ActionEvent actionEvent) {
        //Speaking voice = new Speaking();
        //Speaking.dospeak(enWord.getText());
        Speaking voice = new Speaking();
        voice.say(enWord.getText());
    }

    public void onClickButtonTranslate(ActionEvent actionEvent) {
        String enText = enWord.getText();
        String viText = wordLookUp(enText);
        if("".equals(viText)) {
            viText += "Không có từ. Chuyển sang chế độ online để tìm kiếm.";
        }
        viWord.setText(viText);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            VBox box = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Mode.fxml")));
            drawer.setSidePane(box);

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isOpened()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onClickSpeakerButtonVn(MouseEvent mouseEvent) {
    }
}
