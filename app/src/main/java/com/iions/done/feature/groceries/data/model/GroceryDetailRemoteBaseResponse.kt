package com.iions.done.feature.groceries.data.model

import com.google.gson.annotations.SerializedName

data class GroceryDetailRemoteBaseResponse(
    @SerializedName("item")
    var item: GroceryDetailResponse? = null,
    @SerializedName("inventory_details")
    var groceryInventoryDetails: GroceryDetailInventoryDetails? = null
)

data class GroceryDetailResponse(
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
    @SerializedName("category_id")
    var category_id: Int?,
    @SerializedName("brand_id")
    var brand_id: Int?,
    @SerializedName("images")
    var images: List<GroceryDetailImageResponse>? = null
)

data class GroceryDetailImageResponse(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("original")
    var original: String? = "",
    @SerializedName("thumbnail")
    var thumbnail: String? = ""
)

data class GroceryDetailInventoryDetails(
    @SerializedName("available_quantity")
    var availableQuantity: String? = "",
    @SerializedName("max_price")
    var maxPrice: Int? = 0
)