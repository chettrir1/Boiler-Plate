package com.iions.done.feature.main.data.model

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("modules")
    var modules: List<ModuleResponse>?,
    @SerializedName("grocery")
    var grocery: List<GroceryRemoteResponse>?
)