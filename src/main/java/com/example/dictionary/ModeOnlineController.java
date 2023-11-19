package com.example.dictionary;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class ModeOnlineController {
    @FXML
    protected JFXHamburger hamburger;
    @FXML
    protected JFXDrawer drawer;
    @FXML
    protected TextField enWord;
    @FXML
    protected WebView viWord;
    @FXML
    protected RadioButton EnToVi;
    @FXML
    protected RadioButton ViToEn;
    public final boolean check = true;
    @FXML
    ToggleGroup toggleGroup = new ToggleGroup();


    public void onClickSpeakerButtonEn(ActionEvent actionEvent) {
        String source = enWord.getText();
        if (check) {
            TextToSpeech.playSoundGoogleTranslateViToEn(source);

        }
    }

     public void onClickSpeakerButtonVi() {
         String source = viWord.getEngine().getDocument().getTextContent();;
        if (check) {
            TextToSpeech.playSoundGoogleTranslateEnToVi(source);
        }
    }



     public void onClickedButtonTranslate(ActionEvent event) {
         if (Checker.internetIsConnect()) {
         String text = enWord.getText();
         if (EnToVi.isSelected()) {
             try {
                 viWord.getEngine().loadContent(TranslateAPI.googleTranslate("en", "vi", text));
             } catch (IOException e) {
                 e.printStackTrace();
             }
         } else {
             try {
                 viWord.getEngine().loadContent(TranslateAPI.googleTranslate("vi", "en", text));
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         } else Checker.showWarningAlert();
     }

}
