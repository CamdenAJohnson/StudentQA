package org.Controllers;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import org.Models.User;
import org.AppData.UserDAO;
import org.util.PasswordHasher;
import org.util.SceneManager;
import org.util.UserSession;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();

        try {
            UserDAO.login(username, PasswordHasher.hash(passwordField.getText()));

            if (UserSession.getInstance() != null) {
                System.out.println("Homepage has not be added yet.");
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void handleRegister(ActionEvent event) {
       SceneManager.getInstance().switchScene("/fxml/RegisterPage.fxml");
    }
}
