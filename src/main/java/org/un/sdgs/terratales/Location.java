package org.un.sdgs.terratales;

import javafx.scene.image.Image;

public class Location {
    private String name;
    private String place;
    private String description;
    private int x;
    private int y;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
