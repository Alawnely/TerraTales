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

public class MainController {
    @FXML
    private Label prompt;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void signIn(ActionEvent actionEvent) {
        Main loginApp = new Main();
        if (username.getText().equals("admin")
                && password.getText().equals("1234")) {
            prompt.setText("Login SUCCESS! Access Granted ...");
            prompt.setTextFill(Color.rgb(21, 117, 84));
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                try {
                    loginApp.changeScene(actionEvent,"main-menu.fxml");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            pause.play();
        } else if (username.getText().isEmpty() || password.getText().isEmpty()) {
            prompt.setText("Enter your username & password!");
            prompt.setTextFill(Color.rgb(210, 39, 30));
        } else {
            prompt.setText("Wrong Username or Password!");
            prompt.setTextFill(Color.rgb(210, 39, 30));
        }
    }
}