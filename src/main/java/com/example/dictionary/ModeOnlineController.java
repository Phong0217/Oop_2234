package com.example.dictionary;


import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeOnlineController extends DictionaryManager implements Initializable {
    @FXML
    protected Label headText;
    @FXML
    protected TextField enWord;


    public final boolean check = true;
    @FXML
    ToggleGroup toggleGroup = new ToggleGroup();
    
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
                enWord.setVisible(true);
                EnToVi.setSelected(true);
                ViToEn.setSelected(true);
                definitionView.setVisible(true);

            });
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * method speak button.
     * @throws IOException .
     */
    public void onClickSpeakerButtonEn() throws IOException {
        String text = enWord.getText();
        if (EnToVi.isSelected()) {
            //String source = TranslateAPI.googleTranslate("en", "vi", text);
            TextToSpeech.playSoundGoogleTranslateEnToVi(text);
        } else {
            //String source = TranslateAPI.googleTranslate("vi", "en", text);
            //TextToSpeech.playSoundGoogleTranslateEnToVi(source);
            TextToSpeech.playSoundGoogleTranslateViToEn(text);
        }
    }

     public void onClickSpeakerButtonVi() throws IOException {
         String text = enWord.getText();
         if (EnToVi.isSelected()) {
             String source = TranslateAPI.googleTranslate("en", "vi", text);
             TextToSpeech.playSoundGoogleTranslateViToEn(source);
         } else {
             String source = TranslateAPI.googleTranslate("vi", "en", text);
             TextToSpeech.playSoundGoogleTranslateEnToVi(source);
         }
    }



     public void onClickedButtonTranslate(ActionEvent event) {
         if (Checker.internetIsConnect()) {
         String text = enWord.getText();
         if (EnToVi.isSelected()) {
             try {
                 definitionView.getEngine().loadContent(TranslateAPI.googleTranslate("en", "vi", text));
             } catch (IOException e) {
                 e.printStackTrace();
             }
         } else {
             try {
                 definitionView.getEngine().loadContent(TranslateAPI.googleTranslate("vi", "en", text));
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         } else Checker.showWarningAlert();
     }

}
