package com.example.foodshare;

import android.os.Parcel;
import android.os.Parcelable;

public class RestaurantData implements Parcelable {
    private String name;
    private String address;
    private double rating;
    private String remarks;

    public RestaurantData(String name, String address, double rating, String remarks) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.remarks = remarks;
    }
    public static String[] restaurantNames = {
            "Pizza Palace",
            "Burger Barn",
            "Sushi Sensation",
            "Taco Time",
            "Pasta Paradise",
            "Chicken Coop",
            "Steak House",
            "Burrito Bistro",
            "Salad Spot",
            "Noodle Nest",
            "BBQ Grill",
            "Soup Station",
            "Sandwich Central",
            "Fried Chicken Fiesta",
            "Veggie Delight",
            "Tofu Treats",
            "Dessert Delights",
            "Wrap World",
            "Seafood Shack",
            "Juice Junction",
            "Smoothie Station",
            "Bakery Bliss",
            "Coffee Corner",
            "Tea Time",
            "Ice Cream Island",
            "Milkshake Mansion",
            "Donut Den",
            "Waffle World",
            "Pancake Palace",
            "Omelette Oasis",
            "Eggcellent Eats",
            "Biscuit Bonanza",
            "Bagel Haven",
            "Crepe Cafe",
            "Fruit Stand",
            "Vegetable Village",
            "Meat Market",
            "Baklava Bazaar",
            "Churro Corner",
            "Gelato Garden",
            "Cheesecake Castle",
            "Cookie Crave",
            "Cupcake Kingdom",
            "Candy Carnival",
            "Chocolate Chateau",
            "Popcorn Paradise",
            "Lemonade Lane",
            "Soda Shop",
            "Beverage Boutique"
    };

    protected RestaurantData(Parcel in) {
        name = in.readString();
        address = in.readString();
        rating = in.readDouble();
        remarks = in.readString();
    }

    public static final Creator<RestaurantData> CREATOR = new Creator<RestaurantData>() {
        @Override
        public RestaurantData createFromParcel(Parcel in) {
            return new RestaurantData(in);
        }

        @Override
        public RestaurantData[] newArray(int size) {
            return new RestaurantData[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getRating() {
        return rating;
    }

    public String getRemarks() {
        return remarks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeDouble(rating);
        dest.writeString(remarks);
    }


}
