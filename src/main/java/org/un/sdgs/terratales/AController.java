package org.un.sdgs.terratales;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public abstract class AController {
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
}
