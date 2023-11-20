package com.example.dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class DictionaryManager {


    protected static final String DATA_FILE_PATH = "C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\E_V.txt";
    //private static final String FXML_FILE_PATH = "./src/main/resources/com/example/dictionary/dictionary-view.fxml";

    protected static final String DATA_FILE_PATH_VE = "C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\V_E.txt";
    protected static final String DATAHis_FILE_PATH = "C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\RecentList.txt";
    protected static final String SPLITTING_CHARACTERS = "<html>";


    protected final ArrayList<Word> searchWordTemp = new ArrayList<>();
    protected final ObservableList<String> searchList = FXCollections.observableArrayList();
    NewDictionary evDic = new NewDictionary(DATA_FILE_PATH, DATAHis_FILE_PATH);
    NewDictionary veDic = new NewDictionary(DATA_FILE_PATH_VE, DATAHis_FILE_PATH);





    public static int isContain(String str1, String str2) {

        for (int i = 0; i < Math.min(str1.length(), str2.length()); i++) {
            if (str1.charAt(i) > str2.charAt(i)) {
                return 1;
            } else if (str1.charAt(i) < str2.charAt(i)) {
                return -1;
            }
        }
        if (str1.length() > str2.length()) {
            return 1;
        }
        return 0;
    }

//    protected void saveWordToFile(String path, ArrayList<Word> temp, String spelling, String meaning) {
//        int index = Collections.binarySearch(temp, new Word(spelling, null));
//        if (index >= 0) {
//            temp.get(index).setDef(meaning);
//            NewDictionary evDic = new NewDictionary(DATA_FILE_PATH, DATAHis_FILE_PATH);
//            evDic.updateWordToFile(path, temp);
//        }
//    }

    public void showWarningAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Không có từ nào được chọn!");
        alert.showAndWait();
    }


}


