package org.un.sdgs.terratales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;

public class MapController {
    @FXML
    private ImageView mapImage;
    @FXML
    private Label userLabel;
    @FXML
    private VBox locationsVbox;
    @FXML
    private AnchorPane mapStack;
    private int mapX, mapY, mapZoomLevel;

    @FXML
    private void initialize() {
        userLabel.setText(UserDatabase.currentUser.getUsername());

        mapX = 0;
        mapY = 0;
        mapZoomLevel = 1;

        moveMap("", 0);
    }

    private void moveMap(String movement, int relZoom) {
        // Get original width and height values
        float origWidth = (float) LandDatabase.mapImage.getWidth();
        float origHeight = (float) LandDatabase.mapImage.getHeight();

        // Calculate previous width and height values
        int prevWidth = Math.round(origWidth/mapZoomLevel);
        int prevHeight = Math.round(origHeight/mapZoomLevel);

        // Change zoom level
        mapZoomLevel = Math.max(1, Math.min(4, mapZoomLevel+relZoom));

        // Calculate new width and height values
        int width = Math.round(origWidth/mapZoomLevel);
        int height = Math.round(origHeight/mapZoomLevel);

        // Perform zooming in and out
        if (relZoom != 0) {
            mapX += (prevWidth-width)/2;
            mapY += (prevHeight-height)/2;
        }

        // Perform map movement
        switch (movement) {
            case "^" -> mapY -= 50;
            case "<" -> mapX -= 50;
            case ">" -> mapX += 50;
            case "v" -> mapY += 50;
        }

        // Ensure map is within bounds
        if (mapX < 0) {
            mapX = 0;
        } else if (mapX+width > origWidth) {
            mapX = (int) (origWidth-width);
        }
        if (mapY < 0) {
            mapY = 0;
        } else if (mapY+height > origHeight) {
            mapY = (int) (origHeight-height);
        }

        System.out.println("x bounds = [" + mapX + ", " + (mapX+width) + "] y bounds = [" + mapY + ", " + (mapY+height) + "]");
        //System.out.println("x = " + mapX + ", y = " + mapY + ", width = " + width + ", height = " + height + ", zoom = " + mapZoomLevel);

        // Crop and set to ImageView
        // Source: https://stackoverflow.com/a/15587829
        PixelReader mapPixels = LandDatabase.mapImage.getPixelReader();
        Image croppedMap = new WritableImage(mapPixels, mapX, mapY, width, height);
        mapImage.setImage(croppedMap);

        // Show locations within the bounds
        locationsVbox.getChildren().clear();
        mapStack.getChildren().clear();
        for (Location location : LandDatabase.locationList) {
            int x = location.getX();
            int y = location.getY();
            if (x >= mapX && x <= (mapX+width) && y >= mapY && y <= (mapY+height)) {
                // Create buttons for each visible location
                Button button = createLocationButton(location);
                locationsVbox.getChildren().add(button);

                // Create map markers for each visible location
                double xRel = ((x-mapX)*mapZoomLevel*mapImage.getFitWidth()/LandDatabase.mapImage.getWidth());
                double yRel = ((y-mapY)*mapZoomLevel*mapImage.getFitHeight()/LandDatabase.mapImage.getHeight());
                //System.out.println(location.getName()+" > xRel = "+xRel+", yRel = "+yRel);
                Circle marker = new Circle(3+(mapZoomLevel*2), Color.DARKGREEN);
                marker.setLayoutX(xRel);
                marker.setLayoutY(yRel);
                marker.setOnMouseClicked(mouseEvent -> button.fire());

                Tooltip tooltip = new Tooltip(location.getName()+"\n("+x+", "+y+")");
                tooltip.setShowDelay(Duration.millis(0));
                Tooltip.install(marker, tooltip);

                mapStack.getChildren().add(marker);
            }
        }
    }

    @FXML
    private void onMoveUpAction() {
        moveMap("^", 0);
    }

    @FXML
    private void onMoveLeftAction() {
        moveMap("<", 0);
    }

    @FXML
    private void onMoveRightAction() {
        moveMap(">", 0);
    }

    @FXML
    private void onMoveDownAction() {
        moveMap("v", 0);
    }

    @FXML
    private void onZoomInAction() {
        moveMap("", 1);
    }

    @FXML
    private void onZoomOutAction() {
        moveMap("", -1);
    }

    @FXML
    private void onViewLocationAction(ActionEvent actionEvent) {
        Main app = new Main();
        try {
            app.changeScene(actionEvent,"location-view.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void onViewFavoritesAction(ActionEvent actionEvent) {
        Main app = new Main();
        try {
            app.changeScene(actionEvent,"favorites-view.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void onSignOutAction(ActionEvent actionEvent) {
        Main main = new Main();
        try {
            main.changeScene(actionEvent,"log-in.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Button createLocationButton(Location location) {
        ImageView imageView = new ImageView(location.getImage());
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        Button button = new Button(location.getName(), imageView);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setOnAction(actionEvent -> {
            Main app = new Main();
            try {
                FXMLLoader fxmlLoader = app.changeScene(actionEvent,"location-view.fxml");
                LocationController controller = fxmlLoader.getController();
                controller.changeLocation(LandDatabase.locationList.indexOf(location), true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return button;
    }
}
