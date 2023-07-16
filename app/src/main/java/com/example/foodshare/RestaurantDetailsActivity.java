package com.example.foodshare;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodshare.models.Restaurant;
import com.example.tictactoe.R;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");
        populateRestaurantDetails();

        Button btnRequestDonation = findViewById(R.id.btnRequestDonation);
        btnRequestDonation.setOnClickListener(view -> {
            // Handle donation request button click event
            requestDonation();
        });
    }

    private void populateRestaurantDetails() {
        TextView tvRestaurantName = findViewById(R.id.tvRestaurantName);
        TextView tvQuantityAvailable = findViewById(R.id.tvQuantityAvailable);
        TextView tvAddress = findViewById(R.id.tvAddress);
        TextView tvRating = findViewById(R.id.tvRating);
        TextView tvRemarks = findViewById(R.id.tvRemarks);

        tvRestaurantName.setText(restaurant.getName());
        tvQuantityAvailable.setText(String.valueOf(restaurant.getQuantityAvailable()));
        tvAddress.setText(restaurant.getAddress());
        tvRating.setText(restaurant.getRating());
        tvRemarks.setText(restaurant.getRemarks());
    }

    private void requestDonation() {
        // Perform donation request logic
        // You can implement the database or API interaction here
    }
}
