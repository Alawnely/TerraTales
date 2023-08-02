package org.un.sdgs.terratales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class FavoritesController {

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
    @FXML
    Label userLabel;
    ArrayList<String> favoritesList;
    @FXML
    private void initialize() {
        nameLabel.setVisible(false);
        placeLabel.setVisible(false);
        descriptionLabel.setVisible(false);
        locationImage.setVisible(false);
        setFavoritesList();

        favoritesVbox.getChildren().clear();
        for (String locName : favoritesList){
            Button button = createLocationButton(locName);
            favoritesVbox.getChildren().add(button);
        }
        userLabel.setText(UserDatabase.currentUser.getUsername()+" Favorites");

    }

    public void onBackPress(ActionEvent actionEvent) {
        Main app = new Main();
        try {
            app.changeScene(actionEvent,"map-view.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void setFavoritesList() {
        favoritesList = UserDatabase.currentUser.getFavoritesList();
    }

    private Button createLocationButton(String locName) {
        Image image = new Image(Objects.requireNonNull(getClass().getResource("LocationSRC/" + locName + ".jpg")).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        Button button = new Button(locName, imageView);
        button.setOnAction(mouseEvent -> changeLocation(locName));
        return button;
    }

    private void changeLocation(String locName) {
        for (Location location : LandDatabase.locationList) {
            if (location.getName().equals(locName)){

                nameLabel.setVisible(true);
                placeLabel.setVisible(true);
                descriptionLabel.setVisible(true);
                locationImage.setVisible(true);

                nameLabel.setText(location.getName());
                placeLabel.setText(location.getPlace());
                descriptionLabel.setText(location.getDescription());
                setLocationImage(location.getName());
            }
        }
    }

    private void setLocationImage(String filename){
        //String filelocation = "src/main/resources/org/un/sdgs/terratales/LocationSRC";
        Image image = new Image(Objects.requireNonNull(getClass().getResource("LocationSRC/" + filename + ".jpg")).toExternalForm());
        locationImage.setImage(image);
    }
}
