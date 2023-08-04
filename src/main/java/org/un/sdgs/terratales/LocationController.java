package org.un.sdgs.terratales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class LocationController extends AController {

    @FXML
    private Label nameLabel;
    @FXML
    private Label placeLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private ImageView locationImage;
    @FXML
    private Button favoriteButton;
    @FXML
    private Label userLabel;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;

    private static Location loc;
    private static int locIndex;

    @FXML
    private void initialize() {
        /* Set Initial Index Value */
        locIndex = 0;
        changeLocation(locIndex);
        userLabel.setText(UserDatabase.getInstance().getCurrentUser().getUsername());
        setDropShadow();
    }
    @FXML
    public void onClickNext() {
        if (locIndex+1 <= LandDatabase.getInstance().getLocationList().size()-1) {
            locIndex+=1;
            changeLocation(locIndex);
            System.out.println("Next: "+(locIndex));
        }
    }
    @FXML
    public void onClickPrevious() {
        if (locIndex-1 <= LandDatabase.getInstance().getLocationList().size()-1 && locIndex-1 >= 0) {
            locIndex-=1;
            changeLocation(locIndex);
            System.out.println("Back: "+(locIndex));
        }
    }

    /* Change Screen To Map */
    @FXML
    public void onBackPress(ActionEvent actionEvent) {
        Main app = new Main();
        app.changeScene(actionEvent,"map-view.fxml");
    }

    /* Change Screen To Favorites */
    @FXML
    public void onFavoriteListPress(ActionEvent actionEvent) {
        Main app = new Main();
        app.changeScene(actionEvent,"favorites-view.fxml");
    }

    /* Change Screen To Login */
    public void onSignoutPress(ActionEvent actionEvent) {
        Main app = new Main();
        app.changeScene(actionEvent,"log-in.fxml");
    }
    @FXML
    public void onFavoriteClick() {
        String currentStatus = favoriteButton.getText();
        if (currentStatus.equalsIgnoreCase("Favorite")) {
            favoriteButton.setText("Unfavorite");

            UserDatabase.getInstance().getCurrentUser().addFavorites(loc);
        } else if (currentStatus.equalsIgnoreCase("Unfavorite")) {
            favoriteButton.setText("Favorite");

            UserDatabase.getInstance().getCurrentUser().removeFavorites(loc);
        }
    }

    public void changeLocation(int index) {
        changeLocation(index, false);
    }

    public void changeLocation(int index, boolean peekOnly){
        loc = LandDatabase.getInstance().getLocationList().get(index);

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
        if (UserDatabase.getInstance().getCurrentUser().getFavoritesList().contains(loc)) {
            favoriteButton.setText("Unfavorite");
        }
        else {
            favoriteButton.setText("Favorite");
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
