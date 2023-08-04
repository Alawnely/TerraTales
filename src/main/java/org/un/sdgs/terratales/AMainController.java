package org.un.sdgs.terratales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public abstract class AMainController {
    @FXML
    public Label prompt;
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;

    @FXML
    public abstract void onLogInPress(ActionEvent actionEvent);

    @FXML
    public abstract void onSignUpPress(ActionEvent actionEvent);
}
