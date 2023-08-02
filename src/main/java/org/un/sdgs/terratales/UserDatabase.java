package org.un.sdgs.terratales;

import java.util.ArrayList;

public class UserDatabase {

    public static ArrayList<User> userList = new ArrayList<>();

    public static User currentUser;

    /**
     * Loads Default + Admin Users
     */

    public static void loadDefaultUsers(){

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
}
