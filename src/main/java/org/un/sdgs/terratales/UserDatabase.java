package org.un.sdgs.terratales;

import java.util.ArrayList;

public class UserDatabase implements IDatabase {
    private static UserDatabase instance;

    private final ArrayList<User> userList;

    private User currentUser;

    public UserDatabase() {
        instance = this;
        userList = new ArrayList<>();
    }

    /**
     * Loads Default + Admin Users
     */

    public void load(){

        /* Default Users */
        User user1 = new User("reese", "1234");
        User user2 = new User("xine", "1234");
        User user3 = new User("lawr", "1234");

        /* Admin User */
        User admin = new User("admin", "1234");

        /* Add to User Database */
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(admin);

        /* Debugging */
        System.out.println(userList);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public static UserDatabase getInstance() {
        return instance;
    }
}
