package com.example.foodshare.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseOperations {
    private static Connection connection;

    public DatabaseOperations(Connection connection) {
        this.connection = connection;
    }

    public static boolean insertUser(String username, String email, String password, String userType) {
        String query = "INSERT INTO User (username, email, password, user_type_id) VALUES (?, ?, ?, ?)";
        return executeInsertOrUpdate(query, username, email, password, getUserTypeId(userType));
    }

    private static int getUserTypeId(String userType) {
        String query = "SELECT user_type_id FROM User_Type WHERE user_type_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userType);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("user_type_id");
                } else {
                    return -1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static boolean executeInsertOrUpdate(String query, Object... params) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean validateUserCredentials(String username, String password) {
        String query = "SELECT * FROM User WHERE username = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if a matching user is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add more database operations as needed
}

