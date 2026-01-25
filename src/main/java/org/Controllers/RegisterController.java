package org.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import org.util.PasswordHasher;
import org.AppData.UserDAO;
import org.util.SceneManager;

public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private void handleRegister() {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()) {
            System.out.println("All fields must be filled.");
        } else if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            System.out.println("Passwords do not match.");
        }


    }

    @FXML
    private void handleBackToLogin() {
        SceneManager.getInstance().switchScene("/fxml/LoginPage.fxml");
    }
}
