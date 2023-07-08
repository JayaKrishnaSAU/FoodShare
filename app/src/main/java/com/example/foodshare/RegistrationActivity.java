package com.example.foodshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodshare.database.DatabaseConnection;
import com.example.foodshare.database.DatabaseOperations;
import com.example.tictactoe.R;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText usernameEditText, emailEditText, passwordEditText;
    private Spinner userTypeSpinner;
    private Button signUpButton;

    private String selectedUserType;
    private DatabaseOperations databaseOperations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        userTypeSpinner = findViewById(R.id.userTypeSpinner);
        signUpButton = findViewById(R.id.signUpButton);

        // Set up the user type spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);
        userTypeSpinner.setOnItemSelectedListener(this);

        // Initialize database operations
        databaseOperations = new DatabaseOperations(DatabaseConnection.getConnection());

        // Handle sign up button click
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the user input values
                String username = usernameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Perform validation and handle sign up process
                signUp(username, email, password, selectedUserType);
            }
        });
    }

    private void signUp(String username, String email, String password, String userType) {
        boolean isSuccess = DatabaseOperations.insertUser(username, email, password, userType);

        if (isSuccess) {
            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_SHORT).show();

            // Navigate to RestaurantDetailsActivity
            Intent intent = new Intent(RegistrationActivity.this, RestaurantDetailsActivity.class);
            startActivity(intent);
            finish(); // Optional: Close the RegistrationActivity so the user cannot navigate back to it
        } else {
            Toast.makeText(getApplicationContext(), "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean validateInputs(String username, String email, String password, String userType) {
        // Perform your validation logic here
        // You can check for empty fields, password strength, etc.
        // Return true if the inputs are valid, otherwise false
        // You can also display error messages using Toast or TextViews

        // Example validation (checking for empty fields)
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || userType.isEmpty()) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Get the selected user type from the spinner
        selectedUserType = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Handle the case where nothing is selected
        selectedUserType = "";
    }
}
