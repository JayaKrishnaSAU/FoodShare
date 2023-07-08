package com.example.foodshare

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.foodshare.models.Restaurant
import com.example.tictactoe.R

class RestaurantDetailsActivity : AppCompatActivity() {
    private lateinit var restaurant: Restaurant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_details)

        restaurant = intent.getSerializableExtra("restaurant") as Restaurant
        populateRestaurantDetails()

        val btnRequestDonation: Button = findViewById(R.id.btnRequestDonation)
        btnRequestDonation.setOnClickListener {
            // Handle donation request button click event
            requestDonation()
        }
    }

    private fun populateRestaurantDetails() {
        val tvRestaurantName: TextView = findViewById(R.id.tvRestaurantName)
        val tvQuantityAvailable: TextView = findViewById(R.id.tvQuantityAvailable)
        val tvAddress: TextView = findViewById(R.id.tvAddress)
        val tvRating: TextView = findViewById(R.id.tvRating)
        val tvRemarks: TextView = findViewById(R.id.tvRemarks)

        tvRestaurantName.text = restaurant.name
        tvQuantityAvailable.text = restaurant.quantityAvailable
        tvAddress.text = restaurant.address
        tvRating.text = restaurant.rating
        tvRemarks.text = restaurant.remarks
    }

    private fun requestDonation() {
        // Perform donation request logic
        // You can implement the database or API interaction here
    }
}
