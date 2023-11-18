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

    //Đường dẫn database
    private static final String DB_URL = "jdbc:sqlite:src\\com\\example\\dictionary\\dict_hh.db";

    protected static final String DATA_FILE_PATH = "C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\E_V.txt";
    //private static final String FXML_FILE_PATH = "./src/main/resources/com/example/dictionary/dictionary-view.fxml";

    protected static final String DATA_FILE_PATH_VE = "C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\V_E.txt";
    protected static final String DATAHis_FILE_PATH = "C:\\Users\\ADMIN\\IdeaProjects\\OOP_demo\\OOP_demo\\data\\RecentList.txt";
    protected static final String SPLITTING_CHARACTERS = "<html>";


    protected final ArrayList<Word> searchWordTemp = new ArrayList<>();
    protected final ObservableList<String> searchList = FXCollections.observableArrayList();
    NewDictionary evDic = new NewDictionary(DATA_FILE_PATH, DATAHis_FILE_PATH);
    NewDictionary veDic = new NewDictionary(DATA_FILE_PATH_VE, DATAHis_FILE_PATH);

    /** Kết nối Database. */
    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(DB_URL);
    }

    /** Tìm kiếm từ. */
    public static String wordLookUp(String word_target) {
        String c = "";

        try {
            Connection con = connect();
            String sql = "select * from av where word = '" + word_target + "'";
            //Truy vấn theo tham số sql
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()) {
                c = rs.getString("description");
            }

            con.close();
            rs.close();
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    /** Them tu vao database */
    public static boolean insertData(String word_target, String word_explain) throws SQLException, ClassNotFoundException {
        Connection con = connect();
        String kq = wordLookUp(word_target);
        if ("".equals(kq)) {
            try {
                String sql = "insert into av(word, description) values(?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, word_target);
                ps.setString(2, word_explain);
                int rowAffected = ps.executeUpdate();
                if (rowAffected >= 1) {
                    con.close();
                    ps.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /** Sửa từ. */
    public static boolean updateData(String word_target, String word_explain) throws SQLException, ClassNotFoundException {
        Connection con = connect();
        String kq = wordLookUp(word_target);
        if (!"".equals(kq)) {
            try {
                String sql = "UPDATE av SET description = ? WHERE word == ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, word_explain);
                ps.setString(2, word_target);
                int rowAffected = ps.executeUpdate();
                if (rowAffected >= 1) {
                    con.close();
                    ps.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /** Xóa từ. */
    public static boolean deleteData(String word_target) throws SQLException, ClassNotFoundException {
        Connection con = connect();
        String kq = wordLookUp(word_target);
        if (!"".equals(kq)) {
            try {
                String sql = "DELETE FROM av WHERE word == ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, word_target);
                int rowAffected = ps.executeUpdate();
                if (rowAffected >= 1) {
                    con.close();
                    ps.close();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

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

    protected void saveWordToFile(String path, ArrayList<Word> temp, String spelling, String meaning) {
        int index = Collections.binarySearch(temp, new Word(spelling, null));
        if (index >= 0) {
            temp.get(index).setDef(meaning);
            NewDictionary evDic = new NewDictionary(DATA_FILE_PATH, DATAHis_FILE_PATH);
            evDic.updateWordToFile(path, temp);
        }
    }

    public void showWarningAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Không có từ nào được chọn!");
        alert.showAndWait();
    }


}


