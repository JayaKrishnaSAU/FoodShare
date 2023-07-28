package com.example.foodshare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tictactoe.R;

import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {

    private List<RestaurantData> restaurantList;
    private OnRestaurantClickListener restaurantClickListener;


    public interface OnRestaurantClickListener {
        void onRestaurantClick(int position);
    }

    public RestaurantListAdapter(List<RestaurantData> restaurantList, OnRestaurantClickListener listener) {
        this.restaurantList = restaurantList;
        this.restaurantClickListener = listener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        RestaurantData restaurant = restaurantList.get(position);
        holder.nameTextView.setText(restaurant.getName());
        holder.ratingTextView.setText(String.valueOf(restaurant.getRating()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurantClickListener.onRestaurantClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public void setRestaurantList(List<RestaurantData> restaurantList) {
        this.restaurantList = restaurantList;
        notifyDataSetChanged();
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView ratingTextView;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
        }
    }
}
