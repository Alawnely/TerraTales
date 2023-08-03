package org.un.sdgs.terratales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class LocationController {

    @FXML
    Label nameLabel;
    @FXML
    Label placeLabel;
    @FXML
    Label descriptionLabel;
    @FXML
    ImageView locationImage;
    @FXML
    Button favoriteButton;
    @FXML
    Label userLabel;
    @FXML
    Button previousButton;
    @FXML
    Button nextButton;

    private static Location loc;
    private static int locIndex;

    @FXML
    private void initialize() {
        /* Set Initial Index Value */
        locIndex = 0;
        changeLocation(locIndex);
        userLabel.setText(UserDatabase.currentUser.getUsername());
    }
    @FXML
    public void onClickNext() {

        if (locIndex+1 <= LandDatabase.locationList.size()-1) {
            locIndex+=1;
            changeLocation(locIndex);
            System.out.println("Next: "+(locIndex));
        }
    }
    @FXML
    public void onClickPrevious() {
        if (locIndex-1 <= LandDatabase.locationList.size()-1 && locIndex-1 >= 0) {
            locIndex-=1;
            changeLocation(locIndex);
            System.out.println("Back: "+(locIndex));
        }
    }

    /* Change Screen To Map */
    @FXML
    public void onBackPress(ActionEvent actionEvent) {
        Main app = new Main();
        try {
            app.changeScene(actionEvent,"map-view.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /* Change Screen To Favorites */
    @FXML
    public void onFavoriteListPress(ActionEvent actionEvent) {
        Main app = new Main();
        try {
            app.changeScene(actionEvent,"favorites-view.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /* Change Screen To Login */
    public void onSignoutPress(ActionEvent actionEvent) {
        Main app = new Main();
        try {
            app.changeScene(actionEvent,"sign-in.fxml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    @FXML
    public void onFavoriteClick() {
        if (favoriteButton.getText().equalsIgnoreCase("Favorite")) {
            favoriteButton.setText("Unfavorite");

            UserDatabase.currentUser.addFavorites(loc);
        } else if (favoriteButton.getText().equalsIgnoreCase("Unfavorite")) {
            favoriteButton.setText("Favorite");

            UserDatabase.currentUser.removeFavorites(loc);
        }



    }

    public void changeLocation(int index) {
        changeLocation(index, false);
    }

    public void changeLocation(int index, boolean peekOnly){
        loc = LandDatabase.locationList.get(index);

        nameLabel.setText(loc.getName());
        placeLabel.setText(loc.getPlace());
        descriptionLabel.setText(loc.getDescription());
        locationImage.setImage(loc.getImage());

        /* Hide buttons if fxml is loaded from map view */
        if (peekOnly) {
            previousButton.setVisible(false);
            nextButton.setVisible(false);
        }

        /* Checks If Location Is In Favorites List */
        if (UserDatabase.currentUser.getFavoritesList().contains(loc)) {
            favoriteButton.setText("Unfavorite");
        }
        else {
            favoriteButton.setText("Favorite");
        }

        System.out.println(UserDatabase.currentUser.getFavoritesList());
    }

}
