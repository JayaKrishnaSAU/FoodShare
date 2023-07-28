package com.example.foodshare;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoe.R;

public class RestaurantDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        // Get the restaurant data from the intent
        RestaurantData restaurant = getIntent().getParcelableExtra("restaurant");

        // Set restaurant name
        TextView nameTextView = findViewById(R.id.nameTextView);
        nameTextView.setText(restaurant.getName());

        // Set restaurant address
        TextView addressTextView = findViewById(R.id.addressTextView);
        addressTextView.setText(restaurant.getAddress());

        // Set restaurant rating
        TextView ratingTextView = findViewById(R.id.ratingTextView);
        ratingTextView.setText(String.valueOf(restaurant.getRating()));

        // Set restaurant remarks
        TextView remarksTextView = findViewById(R.id.remarksTextView);
        remarksTextView.setText(restaurant.getRemarks());

        // Set Click Listener for "Request Donation" button
        Button btnRequestDonation = findViewById(R.id.btnRequestDonation);
        btnRequestDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message indicating that the owner will get notified
                Toast.makeText(RestaurantDetailsActivity.this, "Owner will get notified.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
