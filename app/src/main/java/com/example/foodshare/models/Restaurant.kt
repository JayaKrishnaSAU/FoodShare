package com.example.foodshare.models

import java.io.Serializable

data class Restaurant(
    val name: String,
    val quantityAvailable: String,
    val address: String,
    val rating: String,
    val remarks: String
) : Serializable
