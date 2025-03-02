package org.example.apex.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.example.apex.utils.Current;
import org.example.apex.utils.SceneLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {

    @FXML
    private StackPane tableSetter_sp;
    @FXML
    private Label tab_lbl;

    @FXML
    void toMainScene(ActionEvent event) {
        if (!Current.tab.equals("Main")) {
            try {
                tab_lbl.setText("Логин и пароль");
                SceneLoader.reloadStackPane(tableSetter_sp, "scenes/table-scene.fxml");
                Current.tab = "Main";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void toSettingsScene(ActionEvent event) {
        if (!Current.tab.equals("Settings")) {
            try {
                tab_lbl.setText("Настройки");
                SceneLoader.reloadStackPane(tableSetter_sp, "scenes/settings-scene.fxml");
                Current.tab = "Settings";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO
        tab_lbl.setText("Логины и пароли");
        try {
            SceneLoader.reloadStackPane(tableSetter_sp, "scenes/table-scene.fxml");
        } catch (IOException e) {
            tableSetter_sp.getChildren().setAll(new Label("Пока тут ничего нет :("));
            throw new RuntimeException(e);
        }
    }
}
