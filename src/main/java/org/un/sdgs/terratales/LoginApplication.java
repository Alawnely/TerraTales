package com.example.javafx;

// Author: MKC-Cobalt
// Date Created: Aug-16-2022
// Last Modified: Jul-14-2023


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginApplication extends Application {


    public void changeScene(String fxml, ) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        window.getScene().setRoot(fxmlLoader.load());
    }

    public static void main(String[] args) {
        launch();
    }
}