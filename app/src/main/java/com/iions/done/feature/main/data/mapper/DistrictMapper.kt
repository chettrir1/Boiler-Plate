package com.iions.done.feature.main.data.mapper

import com.iions.done.feature.main.data.model.DistrictResponse
import com.iions.entity.DistrictEntity

object DistrictMapper {
    fun mapToLocal(district: List<DistrictResponse>): List<DistrictEntity> {
        return district.map {
            DistrictEntity(
                id = it.id ?: -1,
                name = it.name ?: ""
            )
        }
    }
}