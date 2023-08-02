package org.un.sdgs.terratales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LocationViewTest {

    @FXML
    Label nameLabel;
    @FXML
    Label placeLabel;
    @FXML
    Label descriptionLabel;
    @FXML
    ImageView locationImage;

    private static int locIndex;

    @FXML
    private void initialize() {
        /* Debugging LandDatabase Load*/
        LandDatabase.loadLocations();
        /* Set Initial Index Value */
        locIndex = 0;
        changeLocation(locIndex);
    }
    @FXML
    public void onClickNext(ActionEvent actionEvent) {

        if (locIndex+1 <= LandDatabase.locationList.size()-1) {
            locIndex+=1;
            changeLocation(locIndex);
            System.out.println("Next: "+(locIndex));
        }
    }
    @FXML
    public void onClickBack(ActionEvent actionEvent) {
        if (locIndex-1 <= LandDatabase.locationList.size()-1 && locIndex-1 > 0) {
            locIndex-=1;
            changeLocation(locIndex);
            System.out.println("Back: "+(locIndex));
        }
    }

    private void setLocationImage(String filename){
        //String filelocation = "src/main/resources/org/un/sdgs/terratales/LocationSRC";
        Image image = new Image(getClass().getResource("LocationSRC/"+filename + ".jpg").toExternalForm());
        locationImage.setImage(image);
    }

    private void changeLocation(int index){
        nameLabel.setText(LandDatabase.locationList.get(index).getName());
        placeLabel.setText(LandDatabase.locationList.get(index).getPlace());
        descriptionLabel.setText(LandDatabase.locationList.get(index).getDescription());
        setLocationImage(LandDatabase.locationList.get(index).getName());
    }
}
