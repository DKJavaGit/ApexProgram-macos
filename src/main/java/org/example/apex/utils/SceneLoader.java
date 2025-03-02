package org.example.apex.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.example.apex.Starter;

import java.io.IOException;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;

public class SceneLoader {

    private static final Map<String, Parent> cachedPanes = new HashMap<>(); // Кэш загруженных панелей

    /**
     * Быстро переключает содержимое StackPane, загружая FXML-файл из кэша или с диска.
     *
     * @param pane  StackPane, содержимое которого нужно обновить.
     * @param path  Путь к FXML-файлу, который нужно загрузить.
     * @throws IOException Если произошла ошибка при загрузке FXML-файла.
     */
    public static void reloadStackPane(StackPane pane, String path) throws IOException {
        // Проверяем, что путь не равен null
        Objects.requireNonNull(path, "Path to FXML file cannot be null");

        // Проверяем, есть ли панель в кэше
        Parent mainPane = cachedPanes.get(path);

        // Если панель не найдена в кэше, загружаем ее
        if (mainPane == null) {
            mainPane = FXMLLoader.load(Objects.requireNonNull(Starter.class.getResource(path)));
            cachedPanes.put(path, mainPane); // Сохраняем панель в кэше
        }

        // Очищаем текущее содержимое StackPane и добавляем новое
        pane.getChildren().setAll(mainPane);
    }
}