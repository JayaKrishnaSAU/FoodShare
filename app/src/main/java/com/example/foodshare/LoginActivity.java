package com.example.foodshare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodshare.database.DatabaseConnection;
import com.example.foodshare.database.DatabaseOperations;
import com.example.tictactoe.R;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private TextView registerTextView;
    private EditText usernameEditText, passwordEditText;
    private DatabaseConnection databaseConnection;
    private DatabaseOperations databaseOperations;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        loginButton = findViewById(R.id.loginButton);
        registerTextView = findViewById(R.id.registerTextView);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        // Create database connection
        databaseConnection = new DatabaseConnection(this);
        databaseOperations = new DatabaseOperations(DatabaseConnection.getConnection());

        // Set up click listeners
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve entered username and password
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Validate user credentials
                boolean isValidCredentials = databaseOperations.validateUserCredentials(username, password);
                if (isValidCredentials) {
                    // Login successful, navigate to RestaurantDetailsActivity
                    Intent intent = new Intent(LoginActivity.this, RestaurantDetailsActivity.class);
                    startActivity(intent);
                    finish(); // Close the LoginActivity
                } else {
                    // Login failed, show error message
                    Toast.makeText(LoginActivity.this, "Incorrect credentials. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle register text view click
                // Open the registration activity
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}
