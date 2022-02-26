package com.iions.done.feature.main.data.mapper

import com.iions.done.feature.main.data.model.BrandResponse
import com.iions.entity.GroceryBrandEntity

object GroceryBrandMapper {
    fun mapToLocal(brand: BrandResponse): GroceryBrandEntity {
        return GroceryBrandEntity(
            id = brand.id ?: 0,
            brandName = brand.name ?: "",
            brandLogo = brand.logo ?: ""
        )
    }
}