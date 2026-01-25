package org.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneManager {
    private Stage primaryStage;

    private SceneManager() {}
    private static class Holder {
        private static final SceneManager INSTANCE = new SceneManager();
    }

    public static SceneManager getInstance() {
        return Holder.INSTANCE;
    }

    public void init(Stage stage) {
        if (this.primaryStage == null) {
            this.primaryStage = stage;
        }
    }

    public void switchScene(String fxmlPath) {
        if (fxmlPath.isEmpty()) { return; }

        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            if (primaryStage.getScene() == null) {
                primaryStage.setScene(new Scene(root));
            } else {
                primaryStage.getScene().setRoot(root);
            }
            primaryStage.show();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Failed to load FXML: " + fxmlPath);
        }
    }
}
