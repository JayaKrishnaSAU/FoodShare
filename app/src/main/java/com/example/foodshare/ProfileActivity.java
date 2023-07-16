package com.example.foodshare;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodshare.database.DatabaseConnection;
import com.example.tictactoe.R;
import android.widget.TextView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileActivity extends AppCompatActivity {

    private TextView usernameTextView, emailTextView, userTypeTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize views
        usernameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        userTypeTextView = findViewById(R.id.userTypeTextView);

        // Retrieve user details from the database
        getUserDetails();
    }

    private void getUserDetails() {
        // Get the logged-in user's ID from the session or intent
        int userId = getLoggedInUserId();

        try {
            // Create a database connection
            Connection connection = DatabaseConnection.getConnection();

            // Prepare the SQL query to retrieve user details
            String query = "SELECT username, email, user_type_name " +
                    "FROM user " +
                    "JOIN user_type ON user.user_type_id = user_type.user_type_id " +
                    "WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Check if a user with the given ID exists in the database
            if (resultSet.next()) {
                // Retrieve user details
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String userType = resultSet.getString("user_type_name");

                // Display user details in the TextViews
                usernameTextView.setText(username);
                emailTextView.setText(email);
                userTypeTextView.setText(userType);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int getLoggedInUserId() {
        // Implement the logic to retrieve the logged-in user's ID
        // from the session or intent and return it
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("loggedInUserId", 0);
        return userId;
    }
}
