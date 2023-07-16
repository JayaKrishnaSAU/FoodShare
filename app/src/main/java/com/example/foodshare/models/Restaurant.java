package com.example.foodshare.models;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private int id;
    private String name;
    private int quantityAvailable;
    private String address;
    private String imageResourceId;
    private String rating;
    private String remarks;

    public Restaurant(int id, String name, int quantityAvailable, String address, String imageResourceId, String rating, String remarks) {
        this.id = id;
        this.name = name;
        this.quantityAvailable = quantityAvailable;
        this.address = address;
        this.imageResourceId = imageResourceId;
        this.rating = rating;
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public String getAddress() {
        return address;
    }

    public String getImageResourceId() {
        return imageResourceId;
    }

    public String getRating() {
        return rating;
    }

    public String getRemarks() {
        return remarks;
    }
}
