package org.Models;

public class User {
    private final double userID;
    private final String username;

    public User(int userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    public double getUserID() {
        return userID;
    }
    public String getUsername() { return username; }
}
