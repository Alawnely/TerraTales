package org.un.sdgs.terratales;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import java.io.IOException;

public class MapController extends Application {
    @FXML
    private ImageView mapImage;
    private Image map;
    private int mapX, mapY, mapZoomLevel;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("map-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    private void moveMap(String movement, int relZoom) {
        // Calculate previous width and height values
        int prevWidth = Math.round((float) map.getWidth()/mapZoomLevel);
        int prevHeight = Math.round((float) map.getHeight()/mapZoomLevel);

        // Change zoom level
        mapZoomLevel = Math.max(1, Math.min(5, mapZoomLevel+relZoom));

        // Calculate new width and height values
        int width = Math.round((float) map.getWidth()/mapZoomLevel);
        int height = Math.round((float) map.getHeight()/mapZoomLevel);

        // Perform zooming in and out
        if (relZoom != 0) {
            mapX += (prevWidth-width)/2;
            mapY += (prevHeight-height)/2;
        }

        // Perform map movement
        switch (movement) {
            case "^": mapY -= 50; break;
            case "<": mapX -= 50; break;
            case ">": mapX += 50; break;
            case "v": mapY += 50; break;
        }

        // Ensure map is within bounds
        if (mapX < 0) {
            mapX = 0;
        } else if (width-mapX < 0) {
            mapX = width;
        }
        if (mapY < 0) {
            mapY = 0;
        } else if (height-mapY < 0) {
            mapY = height;
        }

        System.out.println("x bounds = [" + mapX + ", " + (mapX+width) + "] y bounds = [" + mapY + ", " + (mapY+height) + "]");
        //System.out.println("x = " + mapX + ", y = " + mapY + ", width = " + width + ", height = " + height + ", zoom = " + mapZoomLevel);

        // Crop and set to ImageView
        PixelReader mapPixels = map.getPixelReader();
        Image croppedMap = new WritableImage(mapPixels, mapX, mapY, width, height);
        mapImage.setImage(croppedMap);
    }

    @FXML
    private void initialize() {
        map = new Image(getClass().getResource("img/maplightmodegreen_expanded.png").toExternalForm());
        mapX = 0;
        mapY = 0;
        mapZoomLevel = 1;

        moveMap("", 0);
    }

    @FXML
    private void moveUp() {
        moveMap("^", 0);
    }

    @FXML
    private void moveLeft() {
        moveMap("<", 0);
    }

    @FXML
    private void moveRight() {
        moveMap(">", 0);
    }

    @FXML
    private void moveDown() {
        moveMap("v", 0);
    }

    @FXML
    private void zoomIn() {
        moveMap("", 1);
    }

    @FXML
    private void zoomOut() {
        moveMap("", -1);
    }
}
