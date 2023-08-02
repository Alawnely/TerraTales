package org.un.sdgs.terratales;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
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
                String description = LandAttributes[2];
                Location location = new Location(name, place, description);
                locationList.add(location);
            }
            /* Debugging */
            System.out.println(locationList);
        } catch (FileNotFoundException e) {
            System.out.println("File does not Exist");
        }
    }

}
