package com.iions.done.feature.appointment.data.model

import com.google.gson.annotations.SerializedName

data class AppointmentRemoteBaseResponse(
    @SerializedName("appointments")
    var items: AppointmentItemsResponse? = null
)

data class AppointmentItemsResponse(
    @SerializedName("current_page")
    var currentPage: Int,
    @SerializedName("last_page")
    var lastPage: Int,
    @SerializedName("data")
    var data: List<AppointmentResponse>? = null,
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

data class AppointmentResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("description")
    var description: String,
    @SerializedName("contact")
    var contact: String
)
