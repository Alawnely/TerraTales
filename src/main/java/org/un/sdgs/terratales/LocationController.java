package org.un.sdgs.terratales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

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
            favoriteButton.setText("unFavorite");

            /* Check If Location Is Already Added */

            if (UserDatabase.currentUser.getFavoritesList().isEmpty())
            {
                UserDatabase.currentUser.addFavorites(nameLabel.getText());
            }

            /* Loops Through Favorite List of Current User */
            for (int i = 0; i < UserDatabase.currentUser.getFavoritesList().size(); i++) {
                /* If Favorite List Does not Contains Location*/
                if (!UserDatabase.currentUser.getFavoritesList().contains(nameLabel.getText())) {
                    UserDatabase.currentUser.addFavorites(nameLabel.getText());
                }
            }
        } else if (favoriteButton.getText().equalsIgnoreCase("unFavorite")) {
            favoriteButton.setText("Favorite");

            /* Loops Through Favorite List of Current User */
            for (int i = 0; i < UserDatabase.currentUser.getFavoritesList().size(); i++) {
                /* If Favorite Contains Location*/
                if (UserDatabase.currentUser.getFavoritesList().contains(nameLabel.getText())) {
                    UserDatabase.currentUser.removeFavorites(nameLabel.getText());
                }
            }
        }



    }

    private void setLocationImage(String filename){
        //String filelocation = "src/main/resources/org/un/sdgs/terratales/LocationSRC";
        Image image = new Image(Objects.requireNonNull(getClass().getResource("LocationSRC/" + filename + ".jpg")).toExternalForm());
        locationImage.setImage(image);
        locationImage.setPreserveRatio(false);
        locationImage.setSmooth(true);
    }

    public void changeLocation(int index) {
        changeLocation(index, false);
    }

    public void changeLocation(int index, boolean peekOnly){

        nameLabel.setText(LandDatabase.locationList.get(index).getName());
        placeLabel.setText(LandDatabase.locationList.get(index).getPlace());
        descriptionLabel.setText(LandDatabase.locationList.get(index).getDescription());
        setLocationImage(LandDatabase.locationList.get(index).getName());

        /* Hide buttons if fxml is loaded from map view */
        if (peekOnly) {
            previousButton.setVisible(false);
            nextButton.setVisible(false);
        }

        /* Checks If Location Is In Favorites List */

        Location loc = LandDatabase.locationList.get(index);
        for (int i = 0; i < UserDatabase.currentUser.getFavoritesList().size(); i++) {
            if (UserDatabase.currentUser.getFavoritesList().contains(loc.getName())) {
                favoriteButton.setText("Unfavorite");
            }
            else {
                favoriteButton.setText("Favorite");
            }
        }

        System.out.println(UserDatabase.currentUser.getFavoritesList());
    }

}
