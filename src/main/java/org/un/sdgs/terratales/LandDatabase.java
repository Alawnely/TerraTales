package org.un.sdgs.terratales;

import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LandDatabase {

    public static ArrayList<Location> locationList = new ArrayList<>();

    /* Loads Database From Text File*/
    public static void loadLocations() {
        String filelocation = "src/main/resources/org/un/sdgs/terratales/";
        String filename = "locations.txt";
        try {
            FileReader fileReader = new FileReader(filelocation+filename);
            Scanner text = new Scanner(fileReader);
            while (text.hasNext()) {
                String line = text.nextLine();
                String[] LandAttributes = line.split("-");
                String name = LandAttributes[0];
                String place = LandAttributes[1];
                int x = Integer.parseInt(LandAttributes[2]);
                int y = Integer.parseInt(LandAttributes[3]);
                String description = LandAttributes[4];
                Image image = new Image(Objects.requireNonNull(LandDatabase.class.getResource("LocationSRC/" + name + ".jpg")).toExternalForm());
                Location location = new Location(name, place, description, x, y, image);
                locationList.add(location);
            }
            /* Debugging */
            System.out.println(locationList);
        } catch (FileNotFoundException e) {
            System.out.println("File does not Exist");
        }
    }
}
