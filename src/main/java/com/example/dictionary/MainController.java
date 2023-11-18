package com.example.dictionary;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.application.Application;
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
    protected Button buttonSpeak;
    @FXML
    protected Button editButton;
    @FXML
    protected Button removeButton;
    @FXML
    protected HTMLEditor editDefinition;
    @FXML
    protected ListView<String> listView;
    @FXML
    protected WebView definitionView;
    @FXML
    protected TextField searchField;
    @FXML
    protected JFXHamburger hamburger;
    @FXML
    protected JFXDrawer drawer;
    @FXML
    protected TextArea viWord;

    private static final String DATA_FILE_PATH = "C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\E_V.txt";
    //private static final String FXML_FILE_PATH = "./src/main/resources/com/example/dictionary/dictionary-view.fxml";
    private static final String DATAHis_FILE_PATH = "C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\RecentList.txt";
    private static final String SPLITTING_CHARACTERS = "<html>";

    NewDictionary evDic = new NewDictionary(DATA_FILE_PATH, DATAHis_FILE_PATH);


    public void onClickSpeakerButtonEn(ActionEvent actionEvent) throws EngineException {
        //Speaking voice = new Speaking();
        //Speaking.dospeak(enWord.getText());
        Speaking voice = new Speaking();
        voice.doSpeak(searchField.getText());
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


    public void searchFieldAction() throws IOException {
        MainController context = this;
        searchWordTemp.clear();
        searchList.clear();
        String word = context.searchField.getText();
        NewDictionary evDic = new NewDictionary(DATA_FILE_PATH, DATAHis_FILE_PATH);
        int index = evDic.binaryLookup(0, evDic.getVocab().size() - 1, word, evDic.getVocab());
        if (index < 0) {
            Spelling corrector = new Spelling("C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\spelling.txt");
            word = corrector.correct(word);
            index = evDic.binaryLookup(0, evDic.getVocab().size() -1, word, evDic.getVocab());
        }
        updateWordInListView(word, index, evDic.getVocab(), searchWordTemp);
        setSearchListViewItem();

    }

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

    private void setSearchListViewItem() {
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


    protected boolean isOnEditDefinition = false;


    public void showWarningAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Không có từ nào được chọn!");
        alert.showAndWait();
    }

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
        //saveChangeButton.setVisible(true);
        editDefinition.setVisible(true);
        int index = Collections.binarySearch(evDic.getVocab(), new Word(spelling, null));
        String meaning = evDic.getVocab().get(index).getDef();
        editDefinition.setHtmlText(meaning);
    }

    @FXML
    protected void onClickRemoveButton(ActionEvent actionEvent) {
        String spelling = searchField.getText();
        if (spelling.equals("")) {
            showWarningAlert();
            return;
        }
        ButtonType yes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);

        ButtonType no = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xoá từ này không?", yes, no);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == yes) {
            evDic.removeWord(spelling, DATA_FILE_PATH, evDic.getVocab());
            evDic.removeWord(spelling, DATAHis_FILE_PATH, evDic.getHistoryVocab());
 //           evDic.removeWord(spelling, evDic.getBOOKMARK_PATH(), evDic.getBookmarkVocab());
//            headText.setText("Nghĩa của từ");
            searchField.clear();
            definitionView.getEngine().loadContent("");

        }

    }

    public void handleClickBookmarkButton(ActionEvent actionEvent) {
    }

    @FXML
    public void handleClickListView(MouseEvent mouseEvent) {

        String spelling = listView.getSelectionModel().getSelectedItem();

        if (spelling == null) {
            return;
        }
        searchField.setText(spelling);
        int i = -1;
        for (int j = 0; j < evDic.getBookmarkVocab().size(); j++) {

            if (evDic.getBookmarkVocab().get(j).getWord().equals(spelling)) {
                i = j;
                break;
            }
        }
//        if (i >= 0) {
//            bookmarkFalse.setVisible(false);
//            bookmarkTrue.setVisible(true);
//        } else {
//            bookmarkFalse.setVisible(true);
//            bookmarkTrue.setVisible(false);
//        }
//        int index = Collections.binarySearch(evDic.getVocab(), new Word(spelling, null));
//        if (isEVDic) {
//            headText.setText(spelling);
//        } else {
//            String meaning = veDic.getVocab().get(index).getMeaning().substring(9, 9 + spelling.length());
//            headText.setText(meaning);
//        }
    }
}
