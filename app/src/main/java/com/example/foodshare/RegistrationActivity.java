package com.example.foodshare;

import android.os.AsyncTask;
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
import com.example.tictactoe.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText usernameEditText, emailEditText, passwordEditText;
    private Spinner userTypeSpinner;
    private Button signUpButton;

    private int selectedUserTypeId;

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

        // Handle sign up button click
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the user input values
                String username = usernameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Perform validation and handle sign up process
                if (validateInputs(username, email, password, selectedUserTypeId)) {
                    signUp(username, email, password, selectedUserTypeId);
                }
            }
        });
    }

    private boolean validateInputs(String username, String email, String password, int userTypeId) {
        // Perform your validation logic here
        // You can check for empty fields, password strength, etc.
        // Return true if the inputs are valid, otherwise false
        // You can also display error messages using Toast or TextViews

        // Example validation (checking for empty fields)
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || userTypeId == 0) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void signUp(String username, String email, String password, int userTypeId) {
        // Create a new instance of the AsyncTask and execute it
        new SignUpTask().execute(username, email, password, String.valueOf(userTypeId));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Get the selected user type from the spinner
        String selectedUserType = parent.getItemAtPosition(position).toString();
        selectedUserTypeId = getUserTypeId(selectedUserType);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Handle the case where nothing is selected
        selectedUserTypeId = 0;
    }

    private int getUserTypeId(String userType) {
        // Retrieve the user type ID based on the user type name
        // Implement your logic here to map the user type name to its corresponding ID
        if (userType.equals("Donation Center")) {
            return 2;
        } else if (userType.equals("Restaurant Owner")) {
            return 1;
        } else {
            return 0;
        }
    }

    private class SignUpTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            String username = params[0];
            String email = params[1];
            String password = params[2];
            int userTypeId = Integer.parseInt(params[3]);

            try {
                // Create a database connection
                Connection connection = DatabaseConnection.getConnection();

                // Prepare the SQL query to insert the user details into the user table
                String query = "INSERT INTO User (username, email, password, user_type_id) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, email);
                statement.setString(3, password);
                statement.setInt(4, userTypeId);

                // Execute the query
                int rowsInserted = statement.executeUpdate();

                // Close the statement and connection
                statement.close();
                connection.close();

                return rowsInserted > 0;
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean isSuccess) {
            if (isSuccess) {
                // Registration successful
                Toast.makeText(RegistrationActivity.this, "Holaa !!! Your account has been successfully created", Toast.LENGTH_LONG).show();
                try {
                    Thread.sleep(1000);
                    Toast.makeText(RegistrationActivity.this, "Now go back to login page and access your account", Toast.LENGTH_LONG).show();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Toast.makeText(RegistrationActivity.this, "Now go back to login page and access your account", Toast.LENGTH_LONG).show();
            } else {
                // Registration failed
                Toast.makeText(RegistrationActivity.this, "Error occurred. Please try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

