package com.iions.done.feature.main.data.mapper

import com.iions.done.feature.auth.data.model.UserAddressResponse
import com.iions.entity.UserAddressEntity

object UserAddressMapper {
    fun mapToLocal(
        address: List<UserAddressResponse>
    ): List<UserAddressEntity> {
        return address.map { add ->
            UserAddressEntity(
                id = add.id ?: -1,
                districtId = add.district?.id ?: -1,
                district = add.district?.name ?: "",
                streetId = add.street?.id ?: -1,
                street = add.street?.name ?: "",
                localAddress = add.localAddress ?: ""
            )
        }
    }
}