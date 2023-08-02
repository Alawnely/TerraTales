package org.un.sdgs.terratales;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    static Stage window;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        window = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
        window.show();

        /* Debugging LandDatabase Load*/
        LandDatabase.loadLocations();
    }

    public void changeScene(ActionEvent event, String fxml) throws IOException {

        /* Change Scene Code That Factors In Different Size */
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}