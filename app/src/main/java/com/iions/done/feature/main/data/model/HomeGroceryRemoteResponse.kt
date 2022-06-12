package com.iions.done.feature.main.data.model

import com.google.gson.annotations.SerializedName

data class HomeGroceryRemoteResponse(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("items")
    var grocery: List<HomeGroceryResponse>?,
)

data class HomeGroceryResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("sku")
    var sku: String? = "",
    @SerializedName("name")
    var name: String,
    @SerializedName("main_image_thumbnail")
    var image: String?,
    @SerializedName("category_id")
    var categoryId: Int? = 0,
    @SerializedName("brand_id")
    var brandId: Int? = 0,
    @SerializedName("has_variant")
    var hasVarient: Int? = 0,
    @SerializedName("parent_id")
    var parentId: Int? = 0,
    @SerializedName("price")
    var price: Int?,
    @SerializedName("old_price")
    var oldPrice: Int?,
    @SerializedName("has_discount")
    var hasDiscount: Int?,
    @SerializedName("average_rating")
    var rating: Int?
)

data class HomeGroceryCategoryResponse(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("name")
    var name: String? = "",
)