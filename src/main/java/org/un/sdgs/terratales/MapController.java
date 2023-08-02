package org.un.sdgs.terratales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.io.IOException;
import java.util.Objects;

public class MapController {
    @FXML
    private ImageView mapImage;
    @FXML
    private Label userLabel;
    private Image map;
    private int mapX, mapY, mapZoomLevel;

    @FXML
    private void initialize() {
        userLabel.setText(UserDatabase.currentUser.getUsername());

        map = new Image(Objects.requireNonNull(getClass().getResource("img/maplightmodegreen_expanded.png")).toExternalForm());
        mapX = 0;
        mapY = 0;
        mapZoomLevel = 1;

        moveMap("", 0);
    }

    private void moveMap(String movement, int relZoom) {
        // Get original width and height values
        float origWidth = (float) map.getWidth();
        float origHeight = (float) map.getHeight();

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
        PixelReader mapPixels = map.getPixelReader();
        Image croppedMap = new WritableImage(mapPixels, mapX, mapY, width, height);
        mapImage.setImage(croppedMap);
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
            app.changeScene(actionEvent,"location-view-test.fxml");
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
            main.changeScene(actionEvent,"sign-in.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
