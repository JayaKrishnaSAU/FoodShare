package com.example.foodshare.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectionTest {

    private static final String url = "jdbc:mysql://4.tcp.ngrok.io:15584/foodshare";
    private static final String username = "root";
    private static final String password = "password";

    public static void main(String[] args) {
        DBConnectionTest dbConnectionTest = new DBConnectionTest();
        dbConnectionTest.insertUser(7, "adiripola", "irving", "2.5","nothing");
    }

    public void insertUser(int resID, String name, String address, String rating, String remarks) {
        String query = "INSERT INTO user (1, name, email, password, type) VALUES (?, ?, ?, ?, ?)";
        try {
            // Establish a connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Prepare the statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, resID);  // Set the resID parameter using index 1
            statement.setString(2, name);
            statement.setString(3, address);
            statement.setString(4, rating);
            statement.setString(5, remarks);

            // Execute the statement
            int rowsAffected = statement.executeUpdate();

            // Close the statement and connection
            statement.close();
            connection.close();

            // Check if any rows were affected (indicating a successful insert)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getUserTypeId(String userType) {
        String query = "SELECT user_type_id FROM user_type WHERE user_type_name = ?";
        try {
            // Establish a connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Prepare the statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userType);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Extract the user_type_id
            int userTypeId = 0; // Default value if no user type is found
            if (resultSet.next()) {
                userTypeId = resultSet.getInt("user_type_id");
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();

            return userTypeId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
