package org.un.sdgs.terratales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;

public class LoginController extends AMainController {
    @FXML
    public void onLogInPress(ActionEvent actionEvent) {

        String inputUser = username.getText();
        String inputPass = password.getText();

        UserDatabase userDatabase = UserDatabase.getInstance();
        for (User user : userDatabase.getUserList()) {
            if (user.getUsername().equals(inputUser) && user.getPassword().equals(inputPass)) {
                userDatabase.setCurrentUser(user);
                Main app = new Main();
                app.changeScene(actionEvent, "map-view.fxml");
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
        app.changeScene(actionEvent, "sign-up.fxml");
    }
}
