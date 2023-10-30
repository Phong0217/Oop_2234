package com.example.dictionary;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable  {

    @FXML
    private AnchorPane mainContent;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane translatePane;
    @FXML
    private AnchorPane bookmarkPane;
    @FXML
    private AnchorPane historyPane;
    @FXML
    private AnchorPane settingPane;
    @FXML
    public Button translateButton;
    @FXML
    public Button searchButton;
    @FXML
    public Button bookmarkButton;
    @FXML
    public Button mainHistoryButton;
    @FXML
    public Button settingButton;

    @FXML
    private SearchController searchController;
    @FXML
    private HistoryController historyController;
    @FXML
    private TranslateController translateController;
    @FXML
    private BookMarkController bookMarkController;
    @FXML
    private SettingController settingController;
    /**
     * Thiết lập nội dung chính của ứng dụng bằng một AnchorPane cụ thể.
     *
     * Phương thức này được sử dụng để thay đổi nội dung chính của ứng dụng bằng cách thay thế toàn bộ nội dung hiện tại
     * bằng một AnchorPane mới. Nội dung chính của ứng dụng là phần được hiển thị trên giao diện người dùng.
     *
     * @param anchorPane AnchorPane chứa nội dung mới mà bạn muốn hiển thị.
     */
    private void setMainContent(AnchorPane anchorPane) {
            mainContent.getChildren().setAll(anchorPane);
    }
    /**
     * Đặt lại trạng thái giao diện người dùng bằng cách loại bỏ lớp CSS "active" khỏi các nút điều hướng.
     *
     * Phương thức này được sử dụng để đặt lại trạng thái giao diện người dùng bằng cách loại bỏ lớp CSS "active"
     * khỏi các nút điều hướng trong thanh điều hướng của ứng dụng. Điều này giúp bỏ đánh dấu "active" từ các nút khi
     * chuyển đổi giữa các trang hoặc chức năng khác nhau.
     */
    public void resetStyleNav() {
        searchButton.getStyleClass().removeAll("active");
        translateButton.getStyleClass().removeAll("active");
        bookmarkButton.getStyleClass().removeAll("active");
        mainHistoryButton.getStyleClass().removeAll("active");
        settingButton.getStyleClass().removeAll("active");
    }
    // method show view SearchPane
    public void showSearchPane() {
        resetStyleNav(); //reset css for view
        searchButton.getStyleClass().add("active"); //active button search
        searchController.initSearchListView();
        setMainContent(searchPane);
    }

    public void showTranslatePane(ActionEvent actionEvent) {
        resetStyleNav(); //reset css for view
        translateButton.getStyleClass().add("active"); //active button search
        setMainContent(translatePane);
    }

    public void showBookmarkPane(ActionEvent actionEvent) {
        resetStyleNav();
        bookmarkButton.getStyleClass().add("active");
        //bookMarkController.initBookmarkListView();
        setMainContent(bookmarkPane);
    }

    public void showHistoryPane(ActionEvent actionEvent) {
        resetStyleNav();
        mainHistoryButton.getStyleClass().add("active");
        //historyController.initHistoryListView();
        setMainContent(historyPane);
    }

    public void showSettingPane(ActionEvent actionEvent) {
        resetStyleNav();
        settingButton.getStyleClass().add("active");
        settingController.initSettingListView();
        setMainContent(settingPane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
            searchPane = loader.load();
            searchController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("history.fxml"));
            historyPane = loader.load();
            historyController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("setting.fxml"));
            settingPane = loader.load();
            settingController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("translate.fxml"));
            translatePane = loader.load();
            translateController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookmark.fxml"));
            bookmarkPane = loader.load();
            bookMarkController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        searchButton.getStyleClass().add("active");
        mainContent.getChildren().setAll(searchPane);
    }
}
