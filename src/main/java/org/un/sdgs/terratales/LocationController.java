package org.un.sdgs.terratales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

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

    @FXML
    private Button editButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField placeField;
    @FXML
    private TextArea descriptionField;

    private static Location loc;
    private static int locIndex;

    @FXML
    private void initialize() {
        /* Set Initial Index Value */
        try {
            setDropShadow();
            locIndex = 0;

            String username = UserDatabase.getInstance().getCurrentUser().getUsername();
            userLabel.setText(username);
            if (username.equals("admin")) {
                editButton.setVisible(true);
            }
        } catch (NullPointerException ignored) { }
    }
    @FXML
    public void onClickNext() {
        locIndex = (locIndex+1) % LandDatabase.getInstance().getLocationList().size();
        changeLocation(locIndex);
        System.out.println("Next: "+(locIndex));
    }
    @FXML
    public void onClickPrevious() {
        int size = LandDatabase.getInstance().getLocationList().size();
        locIndex = ((locIndex-1) % size + size) % size;
        changeLocation(locIndex);
        System.out.println("Back: "+(locIndex));
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
        locIndex = index;

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
        shadow.setColor(Color.rgb(40, 40, 0, 0.9));
        nameLabel.setEffect(shadow);
        placeLabel.setEffect(shadow);
    }

    @FXML
    public void onEditClick(ActionEvent actionEvent) throws IOException {
        System.out.println("Editing location: "+loc.getName());

        Dialog<ButtonType> dialog = new Dialog<>();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("location-edit.fxml"));
        DialogPane dialogPane = fxmlLoader.load();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Edit Location");
        dialog.setResizable(false);
        dialog.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());

        LocationController controller = fxmlLoader.getController();
        System.out.println(locIndex);
        controller.loadLocationEdit(locIndex);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.APPLY) {
            controller.editLocation(actionEvent);
            changeLocation(locIndex);
        }
    }

    public void loadLocationEdit(int index) {
        loc = LandDatabase.getInstance().getLocationList().get(index);
        locIndex = index;

        nameField.setText(loc.getName());
        placeField.setText(loc.getPlace());
        descriptionField.setText(loc.getDescription());
        locationImage.setImage(loc.getImage());
    }

    @FXML
    public void onChangePicturePress(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.gif", "*.jpeg", "*.jpg", "*.png")
        );
        fileChooser.setInitialDirectory(new File(Objects.requireNonNull(getClass().getResource("locations")).getPath()));
        File file = fileChooser.showOpenDialog(((Node)actionEvent.getSource()).getScene().getWindow());
        if (file != null) {
            locationImage.setImage(new Image(file.getCanonicalPath()));
        }
    }

    public void editLocation(ActionEvent actionEvent) {
        String name = nameField.getText();
        if (name.isBlank()) {
            System.out.println("New location name invalid");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Location name cannot be blank");
            alert.setHeaderText("Invalid name");
            alert.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            alert.getDialogPane().getStylesheets().addAll(((Node)actionEvent.getSource()).getScene().getStylesheets());
            alert.show();
        } else {
            loc.setName(name);
        }
        loc.setPlace(placeField.getText());
        loc.setDescription(descriptionField.getText());
        loc.setImage(locationImage.getImage());
    }
}
