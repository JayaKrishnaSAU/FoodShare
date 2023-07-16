package com.example.foodshare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodshare.models.Restaurant;
import com.example.tictactoe.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RestaurantAdapter restaurantAdapter;
    private List<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize the RecyclerView and its adapter
        RecyclerView restaurantRecyclerView = findViewById(R.id.restaurantRecyclerView);
        restaurantList = new ArrayList<>();
        restaurantAdapter = new RestaurantAdapter(restaurantList);

        // Set the layout manager and adapter for the RecyclerView
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantRecyclerView.setAdapter(restaurantAdapter);

        // Populate the restaurant list (Replace with your actual data retrieval logic)
        populateRestaurantList();

        // Set a click listener for the quantity available link
        restaurantAdapter.setOnQuantityAvailableClickListener(new RestaurantAdapter.OnQuantityAvailableClickListener() {
            @Override
            public void onQuantityAvailableClick(int position) {
                // Handle the click event, e.g., navigate to the restaurant details page
                Restaurant restaurant = restaurantList.get(position);
                Intent intent = new Intent(HomeActivity.this, RestaurantDetailsActivity.class);
                intent.putExtra("restaurant", restaurant);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void populateRestaurantList() {
        // Fetch the restaurant data for the donation center user from the database
        // and add it to the restaurantList

        // Example data
        restaurantList.add(new Restaurant(1, "Restaurant A", 10, "123 Main St", "restaurant_image_1", "4.5", "Good food"));
        restaurantList.add(new Restaurant(2, "Restaurant B", 5, "456 Elm St", "restaurant_image_2", "3.8", "Cozy atmosphere"));
        restaurantList.add(new Restaurant(3, "Restaurant C", 8, "789 Oak St", "restaurant_image_3", "4.2", "Friendly staff"));

        // Notify the adapter that the data set has changed
        restaurantAdapter.notifyDataSetChanged();
    }
}
