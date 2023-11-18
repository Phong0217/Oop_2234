module com.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires javafx.web;
    requires java.sql;
    requires com.jfoenix;
    requires freetts;
    requires jsapi;
    requires controlsfx;


    opens com.example.dictionary to javafx.fxml;
    exports com.example.dictionary;
}