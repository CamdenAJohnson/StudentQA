package org.AppData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseHelper {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/database";
    private static final String DB_USER = "su";
    private static final String DB_PASSWORD = "";

    protected static Connection connection;
    protected static Statement statement = null;

    private DatabaseHelper() {}

    public static void connect() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                statement = connection.createStatement();
                System.out.println("Database connected!");
                statement.execute("DROP ALL OBJECTS"); // Use to clear the database

                createTables();
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        }
    }

    private static void createTables() throws SQLException {
        String userTable = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(255) UNIQUE NOT NULL, "
                + "password VARCHAR(255) NOT NULL)";
        statement.execute(userTable);

        String roleTable = "CREATE TABLE IF NOT EXISTS roles ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "role_name VARCHAR(50) UNIQUE NOT NULL)";
        statement.execute(roleTable);

        String userRolesTable = "CREATE TABLE IF NOT EXISTS user_roles ("
                + "user_id INT NOT NULL, "
                + "role_id INT NOT NULL, "
                + "PRIMARY KEY (user_id, role_id), "
                + "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE, "
                + "FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE)";
        statement.execute(userRolesTable);
    }

    public static void closeConnection() {
        if (connection != null) {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("The statement connection to the database could not be closed.");
                }
            }
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("The Connection to the database could not be closed.");
            }
        }
    }
}
