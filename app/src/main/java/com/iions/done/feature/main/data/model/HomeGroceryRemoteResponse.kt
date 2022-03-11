package com.iions.done.feature.main.data.model

import com.google.gson.annotations.SerializedName

data class HomeGroceryRemoteResponse(
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
    @SerializedName("brand")
    var brand: HomeGroceyBrandResponse? = null,
    @SerializedName("category")
    var category: HomeGroceryCategoryResponse? = null
)

data class HomeGroceryResponse(
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
    var parentId: Int? = 0
)

data class HomeGroceyBrandResponse(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("logo")
    var logo: String? = ""
)

data class HomeGroceryCategoryResponse(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("name")
    var name: String? = ""
)