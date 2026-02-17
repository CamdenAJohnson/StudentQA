package org.Models;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final double userID;
    private final String username;
    private Set<String> roles = new HashSet<>();

    public User(int userID, String username, Set<String> roles) {
        this.userID = userID;
        this.username = username;
        this.roles = roles;
    }

    public double getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    // WIP
    public void addRole(String role) {
        this.roles.add(role);
    }
    // WIP
    public boolean hasRole(String role) {
        return roles.contains(role);
    }
}
