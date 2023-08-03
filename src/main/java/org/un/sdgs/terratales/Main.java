package org.un.sdgs.terratales;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

        /* Loads Default Users*/
        UserDatabase userDatabase = new UserDatabase();
        userDatabase.load();
        /* Debugging LandDatabase Load*/
        LandDatabase landDatabase = new LandDatabase();
        landDatabase.load();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("log-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window.setScene(scene);
        window.setResizable(false);
        window.setTitle("TerraTales");
        window.show();

        /* Sets Current User to First Element of UserDatabase (TEMPORARY) */
        userDatabase.setCurrentUser(userDatabase.getUserList().get(0));
    }

    public FXMLLoader changeScene(ActionEvent event, String fxml) throws IOException {
        /* Change Scene Code That Factors In Different Size */
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(fxml)));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        return fxmlLoader;
    }
}