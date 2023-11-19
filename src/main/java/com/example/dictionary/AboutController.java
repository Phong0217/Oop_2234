package com.example.dictionary;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AboutController implements Initializable {
    public JFXHamburger hamburger;
    public JFXDrawer drawer;

    public AboutController() {
    }

    public void initialize(URL location, ResourceBundle resources) {
        try {
            VBox vbox = (VBox) FXMLLoader.load((URL) Objects.requireNonNull(this.getClass().getResource("Mode.fxml")));
            this.drawer.setSidePane(new Node[]{vbox});

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(this.hamburger);
            transition.setRate(-1.0);

            this.hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {

                transition.setRate(transition.getRate() * -1.0);
                transition.play();
                if (this.drawer.isOpened()) {
                    this.drawer.close();
                } else {
                    this.drawer.open();
                }

            });
        } catch (IOException e) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, (String)null, e);
        }

    }
}
