package com.example.foodshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tictactoe.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RestaurantListActivity extends AppCompatActivity implements RestaurantListAdapter.OnRestaurantClickListener {

    private RecyclerView recyclerView;
    private RestaurantListAdapter adapter;
    private List<RestaurantData> allRestaurants; // List to hold all 50 restaurants
    private List<RestaurantData> visibleRestaurants; // List to hold the currently visible restaurants
    private String selectedLocation = "All"; // The selected location from the dropdown
    private RestaurantData restaurantNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        allRestaurants = new ArrayList<>();
        visibleRestaurants = new ArrayList<>();
        adapter = new RestaurantListAdapter(visibleRestaurants, this);
        recyclerView.setAdapter(adapter);

        // Add dummy restaurant data (50 restaurants)
        addDummyRestaurantData();

        // Set up the Spinner (Dropdown)
        Spinner locationSpinner = findViewById(R.id.locationSpinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.location_options, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(spinnerAdapter);

        // Handle Spinner item selection
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLocation = parent.getItemAtPosition(position).toString();
                updateVisibleRestaurants();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing if nothing is selected
            }
        });

        // Link to the profile page
        TextView profileLinkTextView = findViewById(R.id.profileLinkTextView);
        profileLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantListActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to add dummy restaurant data (50 restaurants)
    private void addDummyRestaurantData() {
        String[] restaurantNames = RestaurantData.restaurantNames;
        String[] locations = getResources().getStringArray(R.array.location_options);

        // Create a HashSet to keep track of restaurant names that have been added
        HashSet<String> addedRestaurantNames = new HashSet<>();

        int restaurantCountPerLocation = 10; // Number of restaurants per location

        // Add dummy restaurant data for the first 10 locations (Dallas, Fort Worth, Plano, Irving)
        for (int i = 0; i < locations.length; i++) {
            String location = locations[i];

            // Iterate over restaurant names and add only if the name is not already added
            for (int j = i * restaurantCountPerLocation; j < i * restaurantCountPerLocation + restaurantCountPerLocation; j++) {
                String nameWithLocation = restaurantNames[j] + ", " + location;
                String address = "Address " + (j + 1);
                double rating = 4.0 + (j * 0.1); // Random ratings from 4.0 to 4.9
                String remarks = "Remarks " + (j + 1);

                // Check if the restaurant name has already been added
                if (!addedRestaurantNames.contains(nameWithLocation)) {
                    RestaurantData restaurant = new RestaurantData(nameWithLocation, address, rating, remarks);
                    allRestaurants.add(restaurant);
                    addedRestaurantNames.add(nameWithLocation); // Add the name to the HashSet to avoid duplicates
                }
            }
        }
        // Initially, show all the restaurants
        visibleRestaurants.addAll(allRestaurants);
        adapter.notifyDataSetChanged();
    }

    // Method to update the visible restaurants based on the selected location
    private void updateVisibleRestaurants() {
        visibleRestaurants.clear();
        for (RestaurantData restaurant : allRestaurants) {
            if (selectedLocation.equals("All") || restaurant.getAddress().contains(selectedLocation)) {
                visibleRestaurants.add(restaurant);
            } else {
                // Add the restaurant name with the selected location at the end
                String nameWithLocation = restaurant.getName() + ", " + selectedLocation;
                visibleRestaurants.add(new RestaurantData(nameWithLocation, "", restaurant.getRating(), ""));
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRestaurantClick(int position) {
        // Handle the click on a restaurant item
        RestaurantData restaurant = visibleRestaurants.get(position);
        // If the restaurant name has the selected location appended to it, you can handle it accordingly.
        // For example, you can show a message saying that the full location address is available in the details page.
        // Then, you can navigate to the details page and pass the restaurant data.
        // For simplicity, I'll just navigate to the details page without appending the selected location.
        Intent intent = new Intent(this, RestaurantDetailsActivity.class);
        intent.putExtra("restaurant", restaurant);
        startActivity(intent);
    }
}

