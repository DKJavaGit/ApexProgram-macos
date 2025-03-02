package org.example.apex;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Starter extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Starter.class.getResource("scenes/main-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MainScene");
        stage.setMinHeight(500);
        stage.setMinWidth(930);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}