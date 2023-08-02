package org.un.sdgs.terratales;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

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
    }

    public void changeScene(String fxml) throws IOException {

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
//        window.getScene().setRoot(fxmlLoader.load());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = fxmlLoader.load();

        // Retrieve the controller from the FXMLLoader
        Object controller = fxmlLoader.getController();

        // Set the new scene with its controller
        window.getScene().setRoot(root);

        // If you want to resize the scene according to the original FXML window size
        if (controller instanceof Initializable) {
            Initializable initializableController = (Initializable) controller;
            initializableController.initialize(null, null);
            Scene scene = new Scene(root, root.prefWidth(-1), root.prefHeight(-1));
            window.setScene(scene);
            window.show();
        }
    }
}