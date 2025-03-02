package org.example.apex.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsSceneController implements Initializable {

    @FXML
    private ChoiceBox<String> algorithms_cb;

    @FXML
    private Rectangle blackTheme_rect;

    @FXML
    private Label engInfo_lbl;

    @FXML
    private ImageView eng_img;

    @FXML
    private Label rusInfo_lbl;

    @FXML
    private ImageView rus_img;

    @FXML
    private CheckBox safeMode_cb;

    @FXML
    private Label whiteTheme_rect;

    @FXML
    void deleteForever(ActionEvent event) {

    }

    @FXML
    void selectEng(MouseEvent event) {
        rusInfo_lbl.setOpacity(0);
        engInfo_lbl.setOpacity(1);
    }

    @FXML
    void selectRus(MouseEvent event) {
        rusInfo_lbl.setOpacity(1);
        engInfo_lbl.setOpacity(0);
    }

    @FXML
    void setBlackTheme(MouseEvent event) {

    }

    @FXML
    void setDefaultSettings(ActionEvent event) {

    }

    @FXML
    void setSettings(ActionEvent event) {

    }

    @FXML
    void setWhiteTheme(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> algo = FXCollections.observableArrayList("NomadCore",
                "AES", "DES", "PBE");
        algorithms_cb.setItems(algo);
        algorithms_cb.setValue(algo.getFirst());
    }
}
