package org.un.sdgs.terratales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class SignupController {
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
            UserDatabase.currentUser = newUser;
            UserDatabase.userList.add(newUser);
            Main app = new Main();
            try {
                app.changeScene(actionEvent, "map-view.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private boolean checkIfUserExists(String inputUser) {
        for (User user : UserDatabase.userList) {
            if (user.getUsername().equalsIgnoreCase(inputUser)) {
                return true;
            }
        }
    return false;
    }

    public void onLogInPress (ActionEvent actionEvent) {
        Main app = new Main();
        try {
            app.changeScene(actionEvent, "log-in.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
//    public void signIn(ActionEvent actionEvent) {
//        //TODO: Update Login Logout According To UserDatabase
//        //TODO: Add Sign In and Sign Out UI
//        //TODO: Add Logout (User must be signed in to access the app)
//
//        Main loginApp = new Main();
//        if (username.getText().equals("admin")
//                && password.getText().equals("1234")) {
//            prompt.setText("Login SUCCESS! Access Granted ...");
//            prompt.setTextFill(Color.rgb(21, 117, 84));
//            PauseTransition pause = new PauseTransition(Duration.seconds(1));
//            pause.setOnFinished(e -> {
//                try {
//                    loginApp.changeScene(actionEvent,"map-view.fxml");
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//            });
//            pause.play();
//        } else if (username.getText().isEmpty() || password.getText().isEmpty()) {
//            prompt.setText("Enter your username & password!");
//            prompt.setTextFill(Color.rgb(210, 39, 30));
//        } else {
//            prompt.setText("Wrong Username or Password!");
//            prompt.setTextFill(Color.rgb(210, 39, 30));
//        }