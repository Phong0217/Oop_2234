package com.example.dictionary;

import com.example.base.Dictionary;
import com.example.base.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class BookMarkController {
    private final ArrayList<Word> bookmarkWordTemp = new ArrayList<>();
    public Button transLanguageEV;
    public Button transLanguageVE;
    public TextField searchField;
    public ListView<String> wordListView;
    public Label headText;

    public Button saveChangeButton;
    public Button bookmarkTrue;
    public Button editButton;
    public Button removeButton;
    public Button bookmarkFalse;
    public Label speakerLanguage1;
    public Button speakerUS;
    public Button speakerUK;
    public Label speakerLanguage2;

    //ObservableList sử dụng để lưu trữ dữ liệu có thể quan sát được (observable),
    //các thành phần giao diện người dùng (UI) có thể tự động cập nhật để hiển thị thay đổi.
    protected ObservableList<String> bookmarkSearch = FXCollections.observableArrayList();

    protected void initBookmarkListView() {
        wordListView.getItems().clear();
        setBookMarkListViewItem();
        setLanguage();
    }

    private void setLanguage() {
    }

    private void setBookMarkListViewItem() {
    }


    public void handleClickTransButton(ActionEvent actionEvent) {
    }

    public void handleBookmarkSearchBar(KeyEvent keyEvent) {
    }

    public void handleClickListView(MouseEvent mouseEvent) {
    }

    public void showBookmarkWordDefinition(MouseEvent mouseEvent) {
    }

    public void handleClickSpeaker1(ActionEvent actionEvent) {
    }

    public void handleClickSpeaker2(ActionEvent actionEvent) {
    }

    public void handleClickSaveButton(ActionEvent actionEvent) {
    }

    public void handleClickBookmarkButton(ActionEvent actionEvent) {
    }

    public void handleClickEditButton(ActionEvent actionEvent) {
    }

    public void handleClickRemoveButton(ActionEvent actionEvent) {
    }
}
