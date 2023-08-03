module org.un.sdgs.terratales {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.un.sdgs.terratales to javafx.fxml;
    exports org.un.sdgs.terratales;
}