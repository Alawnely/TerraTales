package org.un.sdgs.terratales;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.util.Duration;

import java.io.IOException;

public class MenuController {
    @FXML
    protected void onViewLocationClick (ActionEvent actionEvent) {
        Main app = new Main();

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            try {
                app.changeScene("location-view-test.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        pause.play();
    }
}
