package com.example.foodshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodshare.LoginActivity;
import com.example.tictactoe.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        // Find the logout button by its ID
        Button logoutButton = findViewById(R.id.logoutButton);

        // Set OnClickListener to the logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click on the logout button
                // Navigate to the login page
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                // Clear the back stack so that the user cannot navigate back to the ProfileActivity
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                // Finish the ProfileActivity to remove it from the back stack
                finish();
            }
        });
    }
}
