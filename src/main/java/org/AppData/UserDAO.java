package org.AppData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.AppData.DatabaseHelper;
import org.util.PasswordHasher;
import org.Models.User;
import org.util.UserSession;
import static org.AppData.DatabaseHelper.connection;

public class UserDAO {

    public static void insertUser(String username, String hashedPassword) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        if (!username.isEmpty() && !hashedPassword.isEmpty()) {
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, hashedPassword);
                statement.execute();
            } catch (SQLException e) {
                System.err.println("Failed to insert new user into the database. " + e.getMessage());
            }
        }
    }

    public static void login(String username, String hashedPassword) throws SQLException {
        if (username.isEmpty() || hashedPassword.isEmpty()) {
            System.err.println("An empty field was given.");
            return;
        }

        String sql = "SELECT * FROM users WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                if (storedHash.equals(hashedPassword)) {
                    Set<String> roles = getUserRoles(username);
                    User user = new User(rs.getInt("id"), rs.getString("username"), roles);
                    UserSession.getInstance(user.getUsername(), user.getUserID());
                    System.out.println("Login successful for: " + username);
                } else {
                    System.err.println("Invalid password.");
                }
            } else {
                System.err.println("User not found.");
            }
        } catch (SQLException e) {
            System.err.println("Login unsuccessful: " + e.getMessage());
        }
    }

    public static User getUserById(int id) throws SQLException {
        if (id < 0) {
            System.err.println("An invalided id was given.");
            return null;
        }

        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                Set<String> roles = getUserRoles(username);

                user = new User(rs.getInt("id"), username, roles);
            } else {
                System.out.println("No user was found with the given id: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Failed to find user by id. " + e.getMessage());
        }

        return user;
    }

    public static Set<String> getUserRoles(String username) throws SQLException {
        Set<String> roles = new HashSet<>();
        String query = "SELECT r.role_name FROM roles r " +
                "JOIN user_roles ur ON r.id = ur.role_id " +
                "JOIN users u ON u.id = ur.user_id " +
                "WHERE u.username = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                roles.add(rs.getString("role_name"));
            }
        } catch (SQLException e) {
            System.err.println("Failed to find user roles. " +e.getMessage());
        }
        return roles;
    }
}

