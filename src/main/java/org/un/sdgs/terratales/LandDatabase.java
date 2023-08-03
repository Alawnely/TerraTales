package org.un.sdgs.terratales;

import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LandDatabase implements IDatabase {
    private static LandDatabase instance;
    private final ArrayList<Location> locationList;
    private Image mapImage;

    public LandDatabase() {
        instance = this;
        locationList = new ArrayList<>();
    }

    /* Loads Database From Text File*/
    public void load() {
        try {
            String filename = Objects.requireNonNull(LandDatabase.class.getResource("locations.txt")).getFile();
            FileReader fileReader = new FileReader(URLDecoder.decode(filename, StandardCharsets.UTF_8));
            Scanner text = new Scanner(fileReader);
            while (text.hasNext()) {
                String line = text.nextLine();
                String[] LandAttributes = line.split("-");
                String name = LandAttributes[0];
                String place = LandAttributes[1];
                int x = Integer.parseInt(LandAttributes[2]);
                int y = Integer.parseInt(LandAttributes[3]);
                String description = LandAttributes[4];
                Image image = new Image(Objects.requireNonNull(LandDatabase.class.getResource("locations/" + name + ".jpg")).toExternalForm());
                Location location = new Location(name, place, description, x, y, image);
                locationList.add(location);
            }
            /* Debugging */
            System.out.println(locationList);
        } catch (FileNotFoundException e) {
            System.out.println("File does not Exist");
        }

        mapImage = new Image(Objects.requireNonNull(LandDatabase.class.getResource("map/map_final.png")).toExternalForm());
    }

    public ArrayList<Location> getLocationList() {
        return locationList;
    }

    public Image getMapImage() {
        return mapImage;
    }

    public static LandDatabase getInstance() {
        return instance;
    }
}
