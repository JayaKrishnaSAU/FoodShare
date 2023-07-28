package com.example.foodshare;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodshare.database.DatabaseConnection;
import com.example.tictactoe.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView registerLink;

    private Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerLink = findViewById(R.id.registerTextView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate the email and password
                new AsyncLogin(email, password).execute();
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the RegistrationActivity
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    class AsyncLogin extends AsyncTask<Void, Void, String> {
        private String email;
        private String password;

        public AsyncLogin(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                // Create a database connection
                connection = DatabaseConnection.getConnection();

                // Prepare the SQL query to retrieve the user with the given email and password
                String query = "SELECT user_type_id FROM User WHERE email = ? AND password = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, email);
                statement.setString(2, password);

                // Execute the query
                ResultSet resultSet = statement.executeQuery();

                // Check if a user with the given email and password exists in the database
                if (resultSet.next()) {
                    int userTypeId = resultSet.getInt("user_type_id");

                    // Close the result set and statement
                    resultSet.close();
                    statement.close();
                    connection.close();

                    // Fetch the user type name from the User_Type table using the user type ID
                    return getUserTypeName(userTypeId);
                }

                // Close the result set, statement, and connection
                resultSet.close();
                statement.close();
                connection.close();

            } catch (SQLException | NoClassDefFoundError | ClassNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        private String getUserTypeName(int userTypeId) {
            try {
                // Create a new database connection
                connection = DatabaseConnection.getConnection();

                // Prepare the SQL query to retrieve the user type name with the given user type ID
                String query = "SELECT user_type_name FROM User_Type WHERE user_type_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, userTypeId);

                // Execute the query
                ResultSet resultSet = statement.executeQuery();

                // Check if the user type name exists in the database
                if (resultSet.next()) {
                    String userType = resultSet.getString("user_type_name");

                    // Close the result set and statement
                    resultSet.close();
                    statement.close();
                    connection.close();

                    return userType;
                }

                // Close the result set, statement, and connection
                resultSet.close();
                statement.close();
                connection.close();

            } catch (SQLException | NoClassDefFoundError | ClassNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String userType) {
            if (userType != null) {
                // Successful login
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                // Check the user type and navigate to the appropriate activity
                if (userType.equals("Donation Center Organizer")) {
                    // Navigate to the StatusActivity
                    Intent intent = new Intent(LoginActivity.this, StatusActivity.class);
                    startActivity(intent);
                } else if (userType.equals("Restaurant Owner")) {
                    // Navigate to the RestaurantListActivity
                    Intent intent = new Intent(LoginActivity.this, RestaurantListActivity.class);
                    startActivity(intent);
                }
            } else {
                // Failed login
                Toast.makeText(LoginActivity.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
