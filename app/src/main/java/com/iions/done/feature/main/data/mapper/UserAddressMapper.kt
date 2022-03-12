package com.iions.done.feature.main.data.mapper

import com.iions.done.feature.auth.data.model.UserAddressResponse
import com.iions.entity.UserAddressEntity

object UserAddressMapper {
    fun mapToLocal(address: List<UserAddressResponse>): List<UserAddressEntity> {
        return address.map {
            UserAddressEntity(
                id = it.id ?: -1,
                districtId = it.districtId ?: -1,
                streetId = it.streetId ?: -1,
                localAddress = it.localAddress ?: ""
            )
        }
    }
}