module com.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires javafx.web;
    requires java.sql;
    requires jfoenix;
    requires freetts;
    requires jsapi;
    requires controlsfx;
    requires jlayer;


    opens com.example.dictionary to javafx.fxml;
    exports com.example.dictionary;
    opens com.example.application to javafx.fxml;
    exports com.example.application;
}
