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
    private Label welcomeText;
    @FXML
    private Label prompt;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Hello TerraTales!!");
    }

    public void signIn(ActionEvent actionEvent) {
        com.example.javafx.LoginApplication loginApp = new com.example.javafx.LoginApplication();
        if (username.getText().toString().equals("admin")
                && password.getText().toString().equals("1234")) {
            prompt.setText("Login SUCCESS! Access Granted ...");
            prompt.setTextFill(Color.rgb(21, 117, 84));
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                try {
                    loginApp.changeScene("main-menu.fxml",);
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