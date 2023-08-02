package org.un.sdgs.terratales;

import javafx.fxml.FXML;

public class FavoritesController {

    @FXML
    private void initialize() {
        if (UserDatabase.currentUser.getFavoritesList().size() == 0){

        }
    }
}
