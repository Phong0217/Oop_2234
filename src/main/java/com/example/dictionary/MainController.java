package com.example.dictionary;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.speech.EngineException;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController extends DictionaryManager implements Initializable  {
    @FXML
    public Button bookmarkFalse;
    @FXML
    public Button transLanguageEV;
    @FXML
    public Button transLanguageVE;
    @FXML
    protected Button buttonSpeak;
    @FXML
    protected Button editButton;



    /*
    method click button Speak.
     */
    public void onClickSpeakerButtonEn(ActionEvent actionEvent) throws EngineException {

        if (isEVDic) {
            Speaking voice = new Speaking();
            voice.doSpeak(searchField.getText());
        } else {
            TextToSpeech.playSoundGoogleTranslateViToEn(searchField.getText());
        }
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

        for (Word temp : getCurrentDic().getVocab()) {
            searchList.add(temp.getWord());
        }
        listView.setItems(searchList);
    }


    /*
    method xử lý sự kiện khi search từ.
     */
    public void searchFieldAction() throws IOException {

        MainController context = this;
        searchWordTemp.clear();

        searchList.clear();
        String word = context.searchField.getText();

        int index = getCurrentDic().binaryLookup(0, getCurrentDic().getVocab().size() - 1, word, getCurrentDic().getVocab());

        if (index < 0) {
            Spelling corrector = new Spelling("C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\spelling.txt");
            word = corrector.correct(word);
            index = getCurrentDic().binaryLookup(0, getCurrentDic().getVocab().size() -1, word, getCurrentDic().getVocab());
        }
        updateWordInListView(word, index, getCurrentDic().getVocab(), searchWordTemp);
        setSearchListViewItem();

    }


    /*
    method update list view when search words.
     */
    private void updateWordInListView(String word, int index, ArrayList<Word> res, ArrayList<Word> des) {
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

    /*
    method set text for searchField when user clicks a word in listview.
     */
    private void setSearchListViewItem() {
        searchList.clear();

        if (searchField.getText().equals("")) {

            searchWordTemp.clear();
            searchWordTemp.addAll(getCurrentDic().getVocab());
        }
        for (Word temp : searchWordTemp) {
            searchList.add(temp.getWord());
        }
        listView.setItems(searchList);
    }

    /*
    method handle when user click edit button.
     */
    @FXML
    protected void handleClickEditButton(ActionEvent actionEvent) {
        String spelling = searchField.getText();
        if (spelling.equals("")) {
            showWarningAlert();
            return;
        }
        if (isOnEditDefinition) {
            isOnEditDefinition = false;
            editDefinition.setVisible(false);
            //saveChangeButton.setVisible(false);
            return;
        }
        isOnEditDefinition = true;

        editDefinition.setVisible(true);
        int index = Collections.binarySearch(getCurrentDic().getVocab(), new Word(spelling, null));
        String meaning = getCurrentDic().getVocab().get(index).getDef();
        editDefinition.setHtmlText(meaning);
    }
    
    /*
    method show mean of word to webView.
     */
    @FXML
    public void showDefinition() {

        String spelling = listView.getSelectionModel().getSelectedItem();
        if (spelling == null) {
            return;
        }
        int index = Collections.binarySearch(getCurrentDic().getVocab(), new Word(spelling, null));
        String meaning = getCurrentDic().getVocab().get(index).getDef();
        definitionView.getEngine().loadContent(meaning, "text/html");
        if (Collections.binarySearch(getCurrentDic().getHistoryVocab(), new Word(spelling, null)) <= 0) {
            getCurrentDic().addWordToFile(spelling, meaning, getCurrentDic().getHISTORY_PATH());
        }
    }

    /*
    method handle when user click save button.
     */
    @FXML
    public void handleClickBookmarkButton(ActionEvent actionEvent) {
        if (searchField.getText().equals("") && searchField.getText().equals("")) {
            showWarningAlert();
            return;
        }
        if (isEVDic) {
            evDic.modifyWord(searchField.getText(), editDefinition.getHtmlText().replace(" dir=\"ltr\"", ""));
        } else {
            veDic.modifyWord(searchField.getText(), editDefinition.getHtmlText().replace(" dir=\"ltr\"", ""));
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Sửa từ thành công");
        alert.showAndWait();
        showDefinition();

    }

    /*
    method handle when user click word of list view.
     */
    @FXML
    public void handleClickListView(MouseEvent mouseEvent) {

        String spelling = listView.getSelectionModel().getSelectedItem();

        if (spelling == null) {
            return;
        }
        searchField.setText(spelling);
        int i = -1;
        for (int j = 0; j < getCurrentDic().getBookmarkVocab().size(); j++) {

            if (getCurrentDic().getBookmarkVocab().get(j).getWord().equals(spelling)) {
                i = j;
                break;
            }
        }
    }

    /**
     * method clich button switch EV or VE dictionary.
     */
    @FXML
    public void handleClickTransButton() {
        isEVDic = !isEVDic;
        setLanguage();
        clearPane();
    }

    private void clearPane() {
        searchField.clear();
        definitionView.getEngine().loadContent("");
        searchList.clear();
        listView.getItems().clear();
        for (Word temp : getCurrentDic().getVocab()) {
            searchList.add(temp.getWord());
        }
        listView.setItems(searchList);
    }

    private NewDictionary getCurrentDic() {
        if (isEVDic) return evDic;
        else return veDic;
    }

    /**
     * method set user choose language for their library .
     */
    private void setLanguage() {

        if (!isEVDic) {
            transLanguageEV.setVisible(false);
            transLanguageVE.setVisible(true);
        } else {
            transLanguageEV.setVisible(true);
            transLanguageVE.setVisible(false);
        }
    }
}
