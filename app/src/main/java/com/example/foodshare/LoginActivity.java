package com.example.foodshare;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
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

    class AsyncLogin extends AsyncTask<Void, Void, Boolean> {
        private String email;
        private String password;

        public AsyncLogin(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            // Perform the validation logic
            // You can implement your database or API interaction here to validate the credentials
            // For simplicity, let's consider the login as successful if the email is not empty and password is "password"

            try {
                // Create a database connection
                connection = DatabaseConnection.getConnection();

                // Prepare the SQL query to retrieve the user with the given email and password
                String query = "SELECT * FROM user WHERE email = ? AND password = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, email);
                statement.setString(2, password);

                // Execute the query
                ResultSet resultSet = statement.executeQuery();

                // Check if a user with the given email and password exists in the database
                boolean isValidCredentials = resultSet.next();

                // Close the result set, statement, and connection
                resultSet.close();
                statement.close();
                connection.close();

                return isValidCredentials;
            } catch (SQLException | NoClassDefFoundError | ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean isValidCredentials) {
            if (isValidCredentials) {
                // Successful login
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                // Navigate to the RestaurantListActivity
                Intent intent = new Intent(LoginActivity.this, RestaurantListActivity.class);
                startActivity(intent);
            } else {
                // Failed login
                Toast.makeText(LoginActivity.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

