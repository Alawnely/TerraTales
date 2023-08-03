package org.un.sdgs.terratales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;

public class FavoritesController extends AController {

    @FXML
    Label nameLabel;
    @FXML
    Label placeLabel;
    @FXML
    Label descriptionLabel;
    @FXML
    ImageView locationImage;
    @FXML
    VBox favoritesVbox;
    ArrayList<Location> favoritesList;
    @FXML
    Label userLabel;
    @FXML
    private void initialize() {
        nameLabel.setVisible(false);
        placeLabel.setVisible(false);
        descriptionLabel.setVisible(false);
        locationImage.setVisible(false);
        setFavoritesList();

        favoritesVbox.getChildren().clear();
        for (Location location : favoritesList){
            Button button = createLocationButton(location);
            button.setOnAction(mouseEvent -> changeLocation(location));
            favoritesVbox.getChildren().add(button);
        }
        userLabel.setText(UserDatabase.getInstance().getCurrentUser().getUsername());
        setDropShadow();

        System.out.println(UserDatabase.getInstance().getCurrentUser().getFavoritesList());

    }

    private void setFavoritesList() {
        favoritesList = UserDatabase.getInstance().getCurrentUser().getFavoritesList();
    }

    private void changeLocation(Location location) {
        nameLabel.setVisible(true);
        placeLabel.setVisible(true);
        descriptionLabel.setVisible(true);
        locationImage.setVisible(true);

        nameLabel.setText(location.getName());
        placeLabel.setText(location.getPlace());
        descriptionLabel.setText(location.getDescription());
        locationImage.setImage(location.getImage());
    }

    /* Change Screen To Map */
    @FXML
    public void onMapPress(ActionEvent actionEvent) {
        Main app = new Main();
        try {
            app.changeScene(actionEvent,"map-view.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /* Change Screen To Favorites */
    @FXML
    public void onLocationPress(ActionEvent actionEvent) {
        Main app = new Main();
        try {
            app.changeScene(actionEvent,"location-view.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /* Change Screen To Login */
    public void onSignoutPress(ActionEvent actionEvent) {
        Main app = new Main();
        try {
            app.changeScene(actionEvent,"log-in.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void setDropShadow() {
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);
        shadow.setColor(Color.rgb(40,40,0,0.9));
        nameLabel.setEffect(shadow);
        placeLabel.setEffect(shadow);
    }
}
