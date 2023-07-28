package com.example.foodshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoe.R;

public class StatusActivity extends AppCompatActivity {

    private EditText dishNameEditText;
    private EditText quantityEditText;
    private TextView updatedDishTextView;
    private TextView updatedQuantityTextView;
    private Button updateButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        dishNameEditText = findViewById(R.id.dishNameEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        updatedDishTextView = findViewById(R.id.updatedDishTextView);
        updatedQuantityTextView = findViewById(R.id.updatedQuantityTextView);
        updateButton = findViewById(R.id.updateButton);
        logoutButton = findViewById(R.id.logoutButton);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the dish name and quantity from the EditText fields
                String dishName = dishNameEditText.getText().toString().trim();
                String quantityString = quantityEditText.getText().toString().trim();

                // Check if the quantity is a valid number
                try {
                    int quantity = Integer.parseInt(quantityString);

                    // Update the "Updated Dish" and "Updated Quantity" TextViews with the input values
                    updatedDishTextView.setText("Updated Dish: " + dishName);
                    updatedQuantityTextView.setText("Updated Quantity: " + quantity);
                } catch (NumberFormatException e) {
                    // Display an error message if the quantity is not a valid number
                    Toast.makeText(StatusActivity.this, "Invalid quantity format. Please enter a valid number.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the LoginActivity
                Intent intent = new Intent(StatusActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Finish the StatusActivity to prevent going back to it on back press
            }
        });
    }
}
