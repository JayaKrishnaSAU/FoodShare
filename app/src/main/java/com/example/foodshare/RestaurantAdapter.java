package com.example.foodshare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodshare.models.Restaurant;
import com.example.tictactoe.R;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurants;
    private OnQuantityAvailableClickListener quantityAvailableClickListener;

    public RestaurantAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void setOnQuantityAvailableClickListener(OnQuantityAvailableClickListener listener) {
        this.quantityAvailableClickListener = listener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.bind(restaurant);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public interface OnQuantityAvailableClickListener {
        void onQuantityAvailableClick(int position);
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRestaurantName;
        private TextView tvQuantityAvailable;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
//            tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName);
//            tvQuantityAvailable = itemView.findViewById(R.id.tvQuantityAvailable);

            tvQuantityAvailable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (quantityAvailableClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            quantityAvailableClickListener.onQuantityAvailableClick(position);
                        }
                    }
                }
            });
        }

        public void bind(Restaurant restaurant) {
            tvRestaurantName.setText(restaurant.getName());
            tvQuantityAvailable.setText(restaurant.getQuantityAvailable());
        }
    }
}
