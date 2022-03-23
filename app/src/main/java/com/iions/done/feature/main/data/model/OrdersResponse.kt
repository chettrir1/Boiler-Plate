package com.iions.done.feature.main.data.model

import com.google.gson.annotations.SerializedName

class OrdersBaseResponse(
    @SerializedName("orders")
    var orders: List<OrdersResponse>? = emptyList()
)

data class OrdersResponse(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("payment_method")
    var paymentMethod: String?,
    @SerializedName("total_price")
    var totalPrice: Int?,
    @SerializedName("shipping_price")
    var shippingPrice: Int?,
    @SerializedName("created_at")
    var date: String?,
    @SerializedName("status")
    var status: Int?,
    @SerializedName("cart")
    var cart: List<CartResponse>? = emptyList()
)