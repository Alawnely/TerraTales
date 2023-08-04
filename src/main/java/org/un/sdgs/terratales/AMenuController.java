package org.un.sdgs.terratales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public abstract class AMenuController {
    public Button createLocationButton(Location location) {
        ImageView imageView = new ImageView(location.getImage());
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        Button button = new Button(location.getName(), imageView);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.getStyleClass().add("greenline");
        return button;
    }

    public abstract void onViewLocationAction(ActionEvent actionEvent);

    public abstract void onViewMapAction(ActionEvent actionEvent);

    public abstract void onViewFavoritesAction(ActionEvent actionEvent);

    /* Change Screen To Login */
    @FXML
    public void onSignOutPress(ActionEvent actionEvent) {
        Main app = new Main();
        app.changeScene(actionEvent,"log-in.fxml");
    }
}
