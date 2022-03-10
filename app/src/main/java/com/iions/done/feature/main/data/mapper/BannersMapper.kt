package com.iions.done.feature.main.data.mapper

import com.iions.done.feature.main.data.model.BannerResponse
import com.iions.done.feature.main.data.model.ModuleResponse
import com.iions.entity.BannerEntity
import com.iions.entity.ModulesEntity

object BannersMapper {
    fun mapToLocal(banner: List<BannerResponse>): List<BannerEntity> {
        return banner.map {
            BannerEntity(
                id = it.id ?: -1,
                image = "https://d-one.iionstech.com/storage/${it.url}"
            )
        }
    }
}