package org.un.sdgs.terratales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class FavoritesMenuController extends AMenuController {

    @FXML
    private Label nameLabel;
    @FXML
    private Label placeLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private ImageView locationImage;
    @FXML
    private VBox favoritesVbox;
    @FXML
    private Label userLabel;

    @FXML
    private void initialize() {
        nameLabel.setVisible(false);
        placeLabel.setVisible(false);
        descriptionLabel.setVisible(false);
        locationImage.setVisible(false);

        User currentUser = UserDatabase.getInstance().getCurrentUser();
        ArrayList<Location> favoritesList = currentUser.getFavoritesList();
        favoritesVbox.getChildren().clear();
        for (Location location : favoritesList){
            Button button = createLocationButton(location);
            button.setOnAction(mouseEvent -> changeLocation(location));
            favoritesVbox.getChildren().add(button);
        }
        userLabel.setText(currentUser.getUsername());
        setDropShadow();

        System.out.println(currentUser.getFavoritesList());

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
    public void onViewMapAction(ActionEvent actionEvent) {
        Main app = new Main();
        app.changeScene(actionEvent,"map-view.fxml");
    }

    @FXML
    public void onViewFavoritesAction(ActionEvent actionEvent) { }

    /* Change Screen To Favorites */
    @FXML
    public void onViewLocationAction(ActionEvent actionEvent) {
        Main app = new Main();
        FXMLLoader fxmlLoader = app.changeScene(actionEvent,"location-view.fxml");
        LocationMenuController controller = fxmlLoader.getController();
        controller.changeLocation(0);
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
