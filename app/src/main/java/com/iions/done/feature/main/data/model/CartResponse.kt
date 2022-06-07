package com.iions.done.feature.main.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CartBaseResponse(
    @SerializedName("cart")
    var cart: List<CartResponse>? = emptyList()
)

data class CartResponse(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("user_id")
    var userId: Int?,
    @SerializedName("quantity")
    var quantity: Int?,
    @SerializedName("price")
    var price: Int?,
    @SerializedName("cartable")
    var item: CartItemResponse? = null
) : Serializable

data class CartItemResponse(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("image")
    var image: String? = "",
) : Serializable
