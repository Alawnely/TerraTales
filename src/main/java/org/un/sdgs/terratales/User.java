package org.un.sdgs.terratales;

import java.util.ArrayList;

public class User {
    private final String username;
    private final String password;

    private final ArrayList<Location> favoritesList;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        favoritesList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void addFavorites(Location location) {
        favoritesList.add(location);
        System.out.println(username+": fave added "+location.getName());
    }

    public void removeFavorites(Location location) {
        favoritesList.remove(location);
        System.out.println(username+": fave removed " +location.getName());
    }

    public ArrayList<Location> getFavoritesList() {
        return favoritesList;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", favoritesList=" + favoritesList +
                '}';
    }
}
