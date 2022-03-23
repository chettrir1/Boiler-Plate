package com.iions.done.feature.auth.data.model

class AddressResponse(
    var addressId: Int? = null,
    var localAddress: String? = null,
    var districtId: Int?,
    var district: String?,
    var streetId: Int?,
    var street: String?
)