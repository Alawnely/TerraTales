package org.un.sdgs.terratales;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label prompt;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private void initialize() {
    }

    @FXML
    public void onLogInPress(ActionEvent actionEvent) {

        String inputUser = username.getText();
        String inputPass = password.getText();

        UserDatabase userDatabase = UserDatabase.getInstance();
        for (User user : userDatabase.getUserList()) {
            if (user.getUsername().equals(inputUser) && user.getPassword().equals(inputPass)) {
                userDatabase.setCurrentUser(user);
                Main app = new Main();
                try {
                    app.changeScene(actionEvent, "map-view.fxml");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (username.getText().isBlank() || password.getText().isBlank()) {
            prompt.setText("Enter your username & password!");
            prompt.setTextFill(Color.rgb(210, 39, 30));
            } else {
            prompt.setText("Wrong Username or Password!");
            prompt.setTextFill(Color.rgb(210, 39, 30));
            }
        }
    }

    @FXML
    public void onSignUpPress(ActionEvent actionEvent) {
        Main app = new Main();
        try {
            app.changeScene(actionEvent, "sign-up.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
