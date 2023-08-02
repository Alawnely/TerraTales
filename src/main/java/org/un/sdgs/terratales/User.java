package org.un.sdgs.terratales;

import java.util.ArrayList;

public class User {

    private String username;
    private String password;

    private ArrayList<String> favoritesList;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        favoritesList = new ArrayList<String>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getFavoritesList() {
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
