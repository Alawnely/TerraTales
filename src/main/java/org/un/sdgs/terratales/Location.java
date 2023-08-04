package org.un.sdgs.terratales;

import javafx.scene.image.Image;

public class Location {
    private final String name;
    private final String place;
    private final String description;
    private final int x;
    private final int y;
    private Image image;

    public Location(String name, String place, String description, int x, int y, Image image) {
        this.name = name;
        this.place = place;
        this.description = description;
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getDescription() {
        return description;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", description='" + description + '\'' +
                '}';
    }
}
