package com.iions.done.feature.summary.data.model

import com.google.gson.annotations.SerializedName
import com.iions.done.feature.main.data.model.CartResponse
import java.io.Serializable

data class CreateOrderBaseResponse(
    @SerializedName("order")
    var order: CreateOrderResponse
):Serializable

data class CreateOrderResponse(
    @SerializedName("user_id")
    var userId: Int?,
    @SerializedName("unique_id")
    var uniqueId: Int?,
    @SerializedName("total_price")
    var totalPrice: Int?,
    @SerializedName("shipping_price")
    var shippingPrice: Int?,
    @SerializedName("cart")
    var cart: List<CartResponse>? = emptyList()
):Serializable
