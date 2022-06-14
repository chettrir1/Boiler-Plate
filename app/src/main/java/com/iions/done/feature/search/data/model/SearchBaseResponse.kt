package com.iions.done.feature.search.data.model

import com.google.gson.annotations.SerializedName
import com.iions.done.feature.groceries.data.model.GroceryResponse
import com.iions.done.feature.restaurants.data.model.RestaurantResponse

data class SearchBaseResponse(
    @SerializedName("items")
    val groceries: List<GroceryResponse>? = null,
    @SerializedName("restaurants")
    val restaurants: List<RestaurantResponse>? = null
)