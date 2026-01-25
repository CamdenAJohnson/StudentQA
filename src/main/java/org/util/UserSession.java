package org.util;

import java.time.LocalDateTime;

public class UserSession {
    private static UserSession instance;

    private String username;
    private double userID;
    private final LocalDateTime loginTime;

    private UserSession(String username, double ID) {
        this.username = username;
        this.userID = ID;
        this.loginTime = LocalDateTime.now();
    }

    // Call after a successful database authentication
    public static UserSession getInstance(String username, double ID) {
        if (instance == null) {
            instance = new UserSession(username, ID);
        }
        return instance;
    }

    // Call to get the session in other controllers
    public static UserSession getInstance() {
        return instance;
    }

    // Call to log the user out of the current session
    public void cleanUserSession() {
        this.username = null;
        this.userID = -1;
        instance = null;
    }

    // Getters
    public String getUsername() { return username; }
    public LocalDateTime getLoginTime() { return loginTime; }
}
