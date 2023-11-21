package com.example.dictionary;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

public class ManagerController extends DictionaryManager implements Initializable {

    public ToggleGroup data2;
    @FXML
    protected RadioButton addEV;
    @FXML
    protected ToggleGroup data1;
    @FXML
    protected RadioButton addVE;
    @FXML
    protected TextField addText;        /*text of addition.*/
    @FXML
    protected HTMLEditor addEditor;
    @FXML
    protected RadioButton editEV;
    @FXML
    protected RadioButton editVE;


    @FXML
    protected TextField editTextVE;
    @FXML
    protected TextField editTextEV;





    ArrayList<String> wordVE = new ArrayList<>();
    ArrayList<String> wordEV = new ArrayList<>();

    public ManagerController() {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            VBox box = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("Mode.fxml")));

            this.drawer.setSidePane(box);
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
            addWordListEV();
            addWordListVE();
            editDefinition.setVisible(true);
            editEV.setSelected(true);
            addEV.setSelected(true);
            editTextEV.setVisible(true);
            editTextVE.setVisible(false);


            addDefault();
        } catch (IOException e) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void addWordListEV() {
        for (int i = 0; i < evDic.getVocab().size(); i++) {
            wordEV.add(evDic.getVocab().get(i).getWord());
        }
    }

    public void addWordListVE() {
        for (int i = 0; i < veDic.getVocab().size(); i++) {
            wordVE.add(veDic.getVocab().get(i).getWord());
        }
    }

    /**
     * method handle click Arrow button.
     */

    @FXML
    public void handleClickArrow() {
        if (addText.getText().equals("")) {
            showWarningAlert();
            return;
        }
        addEditor.setHtmlText("<html>" + addText.getText() + " /" + addText.getText() + "/"
                + "<ul><li><b><i> loại từ: </i></b><ul><li><font color='#cc0000'><b> Nghĩa thứ nhất: </b></font><ul></li></ul></ul></li></ul><ul><li><b><i>loại từ khác: </i></b><ul><li><font color='#cc0000'><b> Nghĩa thứ hai: </b></font></li></ul></li></ul></html>");
    }


    public void addDefault() {
        addText.clear();
        addEditor.setHtmlText("");
    }

    /**
     * method reset Web view.
     */
    @FXML
    public void addReset() {
        addEditor.setHtmlText("<html>" + addText.getText() + " /" + addText.getText() + "/"
                + "<ul><li><b><i> loại từ: </i></b><ul><li><font color='#cc0000'><b> Nghĩa thứ nhất: </b></font><ul></li></ul></ul></li></ul><ul><li><b><i>loại từ khác: </i></b><ul><li><font color='#cc0000'><b> Nghĩa thứ hai: </b></font></li></ul></li></ul></html>");
    }

    /**
     * method handle click Add button.
     */
    @FXML
    void add() {
        String meaning = addEditor.getHtmlText().replace(" dir=\"ltr\"", "");
        if (addText.getText().equals("")) {
            showWarningAlert();
            return;
        }
        if (getDictionaryToAdd().addWord(addText.getText(), meaning)) {
            addReset();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Thêm từ thành công");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Từ bạn thêm đã tồn tại! Hãy chọn chức năng sửa đổi!");
            alert.showAndWait();
        }
    }

    private NewDictionary getDictionaryToAdd() {
        if (addEV.isSelected()) {
            return evDic;
        } else {
            return veDic;
        }
    }

    @FXML
    protected void handleClickEVButton() {
        editTextEV.setVisible(true);
        editTextVE.setVisible(false);

    }

    @FXML
    protected void handleClickVEButton() {
        editTextEV.setVisible(false);
        editTextVE.setVisible(true);


    }

    /**
     * method click Arrow button.
     */
    @FXML
    public void handleClickEditArrow() {
        String a;
        editDefinition.setVisible(true);
        if (editEV.isSelected()) {
            a = editTextEV.getText();
        } else {
            a = editTextVE.getText();
        }
        int index = Collections.binarySearch(getDictionary().getVocab(), new Word(a, null));
        String b = getDictionary().getVocab().get(index).getDef();
        editDefinition.setHtmlText(b);
    }

    /**
     * method click saveButton.
     */
    @FXML
    public void save() {
        if (editTextEV.getText().equals("") && editTextVE.getText().equals("")) {
            showWarningAlert();
            return;
        }
        if (editEV.isSelected()) {
            evDic.modifyWord(editTextEV.getText(), editDefinition.getHtmlText().replace(" dir=\"ltr\"", ""));
        } else {
            veDic.modifyWord(editTextVE.getText(), editDefinition.getHtmlText().replace(" dir=\"ltr\"", ""));
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Sửa từ thành công");
        alert.showAndWait();
        showDefinition();
    }

    private void showDefinition() {
            String a;
            if (editEV.isSelected()) {
                a = editTextEV.getText();
            } else {
                a = editTextVE.getText();
            }
            int index = Collections.binarySearch(getDictionary().getVocab(), new Word(a, null));
            String b = getDictionary().getVocab().get(index).getDef();
        definitionView.getEngine().loadContent(b, "text/html");

    }

    /**
     * method .
     * @return EV or VE dictionary.
     */
    private NewDictionary getDictionary() {
        if (editEV.isSelected()) {
            return evDic;
        } else {
            return veDic;
        }
    }

    /**
     * method remove word .
     */
    @FXML
    public void remove() {
        if (editTextEV.getText().equals("") || editTextVE.getText().equals("")) {
            showWarningAlert();
            return;
        }
        if (editEV.isSelected()) {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xoá từ này không?", yes, no);

            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == yes) {
                getDictionary().removeWord(editTextEV.getText(), getDictionary().getPATH(), getDictionary().getVocab());
                editTextEV.clear();
                editDefinition.setHtmlText("");
            }
        } else {

            ButtonType yes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xoá từ này không?", yes, no);

            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == yes) {
                getDictionary().removeWord(editTextVE.getText(), getDictionary().getPATH(), getDictionary().getVocab());
                editTextVE.clear();
                editDefinition.setHtmlText("");
            }
        }
    }


}
