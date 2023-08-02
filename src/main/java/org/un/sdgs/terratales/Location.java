package org.un.sdgs.terratales;

public class Location {
    private String name;
    private String place;
    private String description;

    public Location(String name, String place, String description) {
        this.name = name;
        this.place = place;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
