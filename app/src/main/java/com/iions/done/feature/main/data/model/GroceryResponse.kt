package com.iions.done.feature.main.data.model

import com.google.gson.annotations.SerializedName

data class GroceryResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("sku")
    var sku: String? = "",
    @SerializedName("name")
    var name: String,
    @SerializedName("cover_image")
    var image: String?,
    @SerializedName("category_id")
    var categoryId: Int? = 0,
    @SerializedName("brand_id")
    var brandId: Int? = 0,
    @SerializedName("has_variant")
    var hasVarient: Int? = 0,
    @SerializedName("parent_id")
    var parentId: Int? = 0,
    @SerializedName("brand")
    var brand: BrandResponse?
)

data class BrandResponse(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("logo")
    var logo: String? = ""
)