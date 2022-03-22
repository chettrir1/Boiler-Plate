package com.iions.done.feature.main.data.mapper

import com.iions.done.feature.main.data.model.HomeGroceryRemoteResponse
import com.iions.entity.GroceryCategoryEntity

object GroceryCategoryMapper {
    fun mapToLocal(category: List<HomeGroceryRemoteResponse>): List<GroceryCategoryEntity> {
        return category.map {
            GroceryCategoryEntity(
                id = it.id ?: 0,
                categoryName = it.name ?: ""
            )
        }
    }
}