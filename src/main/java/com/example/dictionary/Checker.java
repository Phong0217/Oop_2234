package com.example.dictionary;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class Checker {

    public Checker() {

    }

    public static void showResult(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Kết Quả");
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static boolean internetIsConnect() {
        try {
            URL url = new URL("https://www.google.com");
            URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();

            return true;
        } catch (MalformedURLException e1) {
            throw new RuntimeException(e1);
        } catch (IOException e2) {
            return false;
        }
    }

    public static void showWarningAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Error connect Internet, check connection Internet and try again !!");
        alert.showAndWait();
    }


}
