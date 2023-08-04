package org.un.sdgs.terratales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class SignupController {
    @FXML
    private Label prompt;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public void onSignUpPress(ActionEvent actionEvent) {

        String inputUser = username.getText();
        String inputPass = password.getText();

        if (checkIfUserExists(inputUser)) {
            prompt.setText("User already exists.");
            prompt.setTextFill(Color.rgb(210, 39, 30));
        } else if (username.getText().isBlank() || password.getText().isBlank()) {
            prompt.setText("Enter your username & password!");
            prompt.setTextFill(Color.rgb(210, 39, 30));
        } else {
            User newUser = new User(inputUser, inputPass);
            UserDatabase userDatabase = UserDatabase.getInstance();
            userDatabase.getUserList().add(newUser);
            userDatabase.setCurrentUser(newUser);
            Main app = new Main();
            app.changeScene(actionEvent, "map-view.fxml");
        }
    }

    private boolean checkIfUserExists(String inputUser) {
        for (User user : UserDatabase.getInstance().getUserList()) {
            if (user.getUsername().equalsIgnoreCase(inputUser)) {
                return true;
            }
        }
    return false;
    }

    public void onLogInPress(ActionEvent actionEvent) {
        Main app = new Main();
        app.changeScene(actionEvent, "log-in.fxml");
    }
}
