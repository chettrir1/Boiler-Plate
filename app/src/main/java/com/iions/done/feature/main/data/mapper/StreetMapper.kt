package com.iions.done.feature.main.data.mapper

import com.iions.done.feature.main.data.model.StreetResponse
import com.iions.entity.StreetEntity

object StreetMapper {
    fun mapToLocal(street: List<StreetResponse>): List<StreetEntity> {
        return street.map {
            StreetEntity(
                id = it.id ?: -1,
                name = it.name ?: "",
                districtId = it.districtId ?: -1
            )
        }
    }
}