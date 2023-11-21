package com.example.dictionary;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public abstract class DictionaryManager {

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
    protected RadioButton EnToVi;
    @FXML
    protected RadioButton ViToEn;


    protected static final String DATA_FILE_PATH = "C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\E_V.txt";

    protected static final String DATA_FILE_PATH_VE = "C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\V_E.txt";
    protected static final String DATAHis_FILE_PATH = "C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\RecentList.txt";

    @FXML
    protected Map<String, Word> data = new HashMap<>();
    /*
    flag define library E_V or V_E.
     */
    protected static boolean isEVDic;

    /*
    flag define when user click button edit or non.
     */
    protected boolean isOnEditDefinition = false;

    /**
     * Danh sách tạm thời chứa các đối tượng Word kết quả từ việc tìm kiếm.
     */
    protected final ArrayList<Word> searchWordTemp = new ArrayList<>();

    /**
     * Danh sách chứa các từ kết quả tìm kiếm, sử dụng để hiển thị trên ListView.
     */
    protected final ObservableList<String> searchList = FXCollections.observableArrayList();

    /**
     * Đối tượng từ điển Anh-Việt được khởi tạo từ đường dẫn dữ liệu và lịch sử chỉ định.
     */
    NewDictionary evDic = new NewDictionary(DATA_FILE_PATH, DATAHis_FILE_PATH);

    /**
     * Đối tượng từ điển Việt-Anh được khởi tạo từ đường dẫn dữ liệu và lịch sử chỉ định.
     */
    NewDictionary veDic = new NewDictionary(DATA_FILE_PATH_VE, DATAHis_FILE_PATH);





    /**
     * Compares two strings lexicographically, character by character, and determines their relationship.
     * The comparison is case-sensitive and considers the ASCII values of characters.
     *
     * @param str1 The first string to be compared.
     * @param str2 The second string to be compared.
     * @return 1 if str1 is lexicographically greater than str2,
     *          -1 if str1 is lexicographically less than str2,
     *         and 0 if both strings are lexicographically equal.
     *
     * @throws NullPointerException if either str1 or str2 is null.
     *
     * @see String#charAt(int)
     * @see Character#compare(char, char)
     */
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

    /*
    method thông báo cho user.
     */
    public void showWarningAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Không có từ nào được chọn!");
        alert.showAndWait();
    }


}