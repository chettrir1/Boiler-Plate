package com.iions.done.feature.groceries.data.model

import com.google.gson.annotations.SerializedName

data class GroceryRemoteBaseResponse(
    @SerializedName("items")
    var items: GroceryItemsResponse? = null,
    @SerializedName("brands")
    var brand: List<GroceryBrandResponse>? = null,
    @SerializedName("categories")
    var category: List<GroceryCategoryResponse>? = null
)

data class GroceryItemsResponse(
    @SerializedName("current_page")
    var currentPage: Int,
    @SerializedName("data")
    var data: List<GroceryResponse>? = null,
    @SerializedName("first_page_url")
    var firstPageUrl: String? = "",
    @SerializedName("from")
    var from: Int,
    @SerializedName("last_page_url")
    var lastPageUrl: String? = "",
    @SerializedName("next_page_url")
    var nextPageUrl: String? = "",
    @SerializedName("per_page")
    var perPage: Int? = 0,
    @SerializedName("total")
    var totalPage: Int? = 0
)

data class GroceryResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("sku")
    var sku: String? = "",
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("main_image_original")
    var mainImageOriginal: String?,
    @SerializedName("main_image_thumbnail")
    var mainImageThumbnail: String?,
    @SerializedName("category")
    var category: GroceryCategoryResponse?,
    @SerializedName("brand")
    var brand: GroceryBrandResponse?,
    @SerializedName("quantity")
    var quantity: String?,
    @SerializedName("current_price")
    var currentPrice: String?,
    @SerializedName("old_price")
    var oldPrice: String?,
    @SerializedName("has_discount")
    var hasDiscount: Boolean?,
    @SerializedName("images")
    var images: List<GroceryImageResponse>? = null
)

data class GroceryBrandResponse(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("logo")
    var logo: String? = ""
)

data class GroceryCategoryResponse(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("name")
    var name: String? = ""
)

data class GroceryImageResponse(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("original")
    var original: String? = "",
    @SerializedName("thumbnail")
    var thumbnail: String? = ""
)