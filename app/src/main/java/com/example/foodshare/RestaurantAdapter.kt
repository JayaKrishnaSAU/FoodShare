package com.example.foodshare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodshare.models.Restaurant
import com.example.tictactoe.R


class RestaurantAdapter(
    private var restaurants: List<Restaurant>,
    private val onItemClick: (Restaurant) -> Unit
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

    override fun getItemCount(): Int = restaurants.size

    fun updateRestaurants(newRestaurants: List<Restaurant>) {
        restaurants = newRestaurants
        notifyDataSetChanged()
    }

    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvRestaurantName: TextView = itemView.findViewById(R.id.tvRestaurantName)
        private val tvQuantityAvailable: TextView = itemView.findViewById(R.id.tvQuantityAvailable)

        fun bind(restaurant: Restaurant) {
            tvRestaurantName.text = restaurant.name
            tvQuantityAvailable.text = restaurant.quantityAvailable

            itemView.setOnClickListener {
                onItemClick(restaurant)
            }
        }
    }
}
