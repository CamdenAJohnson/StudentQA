package org.AppStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.AppData.DatabaseHelper;
import org.util.SceneManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseHelper.connect();
        primaryStage.setTitle("StudentQA");
        SceneManager.getInstance().init(primaryStage);
        SceneManager.getInstance().switchScene("/fxml/LoginPage.fxml");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        DatabaseHelper.closeConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }
}