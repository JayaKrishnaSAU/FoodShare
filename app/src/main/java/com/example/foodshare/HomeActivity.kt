package com.example.foodshare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodshare.models.Restaurant
import com.example.tictactoe.R

class HomeActivity : AppCompatActivity() {
    private lateinit var restaurantList: RecyclerView
    private lateinit var restaurantAdapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        restaurantList = findViewById(R.id.restaurantList)
        restaurantAdapter = RestaurantAdapter(emptyList()) { restaurant ->
            // Handle restaurant item click event
            navigateToRestaurantDetails(restaurant)
        }
        restaurantList.layoutManager = LinearLayoutManager(this)
        restaurantList.adapter = restaurantAdapter

        // Populate restaurant data (replace with your data)
        val restaurants = listOf(
            Restaurant("Restaurant 1", "10", "Address 1", "4.5", "Good food"),
            Restaurant("Restaurant 2", "5", "Address 2", "4.2", "Nice ambiance")
        )
        restaurantAdapter.updateRestaurants(restaurants)
    }

    private fun navigateToRestaurantDetails(restaurant: Restaurant) {
        val intent = Intent(this, RestaurantDetailsActivity::class.java)
        intent.putExtra("restaurant", restaurant)
        startActivity(intent)
    }
}