package com.example.foodshare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodshare.database.DatabaseConnection;
import com.example.tictactoe.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RestaurantListActivity extends AppCompatActivity {

    private LinearLayout restaurantListLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        restaurantListLayout = findViewById(R.id.restaurantListLayout);
// Inside the onCreate() method of RestaurantListActivity
        TextView profileLinkTextView = findViewById(R.id.profileLinkTextView);
        profileLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the profile page
                Intent intent = new Intent(RestaurantListActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        // Fetch restaurant data from the database
        fetchRestaurantData();
    }

    private void fetchRestaurantData() {
        try {
            // Create a database connection
            Connection connection = DatabaseConnection.getConnection();

            // Prepare the SQL query to retrieve restaurant data
            String query = "SELECT name, address FROM restaurant";
            PreparedStatement statement = connection.prepareStatement(query);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Process the restaurant data
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");

                // Display the restaurant information
                displayRestaurantInfo(name, address);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void displayRestaurantInfo(String name, String address) {
        // Inflate the restaurant item layout
        LinearLayout restaurantItemLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.restaurant_item, null);

        // Find the views within the restaurant item layout
        ImageView restaurantImage = restaurantItemLayout.findViewById(R.id.restaurantImage);
        TextView nameTextView = restaurantItemLayout.findViewById(R.id.nameTextView);
        TextView addressTextView = restaurantItemLayout.findViewById(R.id.addressTextView);

        // Set the restaurant information to the views
        nameTextView.setText(name);
        addressTextView.setText(address);
        restaurantImage.setImageResource(R.drawable.restaurant); // Set the image resource

        // Add the restaurant item layout to the restaurantListLayout
        restaurantListLayout.addView(restaurantItemLayout);
    }

}
